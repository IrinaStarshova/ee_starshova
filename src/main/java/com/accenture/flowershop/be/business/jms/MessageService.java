package com.accenture.flowershop.be.business.jms;

import com.accenture.flowershop.be.business.UserMarshallingService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.*;
import java.io.*;
import java.net.ConnectException;

@Component
public class MessageService {
    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    @Qualifier("outDestination")
    private Destination outDestination;
    @Autowired
    @Qualifier("inDestination")
    private Destination inDestination;
    @Value("${filePath}")
    private String filePath;
    @Autowired
    private UserMarshallingService userMarshallingService;
    @Autowired
    private UserBusinessService userBusinessService;
    private static final Logger LOG = LoggerFactory.getLogger(MessageService.class);

    public void sendCustomerXML(String filename) throws IOException {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            BufferedReader in = new BufferedReader(new FileReader(filePath + "/" + filename + ".xml"));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                builder.append(line);
            }
            TextMessage testMessage = session.createTextMessage(builder.toString());
            MessageProducer producer = session.createProducer(outDestination);
            producer.send(testMessage);
        } catch (JMSException e) {
            if (e.getCause().getClass().equals(ConnectException.class))
                LOG.warn("ActiveMQ connection error!");
            else
                LOG.warn("Error while sending to queue");
        }
    }

    @PostConstruct
    public void receiveAndChangeDiscount() {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(inDestination);
            connection.start();
            consumer.setMessageListener(message -> {
                try {
                    DiscountRequestObject dro = (DiscountRequestObject)
                            userMarshallingService.convertXMLStringToObject(((TextMessage) message).getText());
                    userBusinessService.changeDiscount(dro.getLogin(), dro.getDiscount());
                } catch (JMSException | IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (JMSException e) {
            if (e.getCause().getClass().equals(ConnectException.class))
                LOG.warn("ActiveMQ connection error!");
            else
                LOG.warn("Error while listening to queue");
        }
    }
}
