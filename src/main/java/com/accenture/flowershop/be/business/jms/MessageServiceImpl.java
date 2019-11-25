package com.accenture.flowershop.be.business.jms;

import com.accenture.flowershop.be.business.UserMarshallingService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import org.apache.activemq.ActiveMQConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;

@Component
public class MessageServiceImpl implements MessageService {
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

    private static Connection connection;
    private Session session;

    private static final Logger LOG = LoggerFactory.getLogger(MessageService.class);

    @Override
    public void sendCustomerXML(String filename) throws IOException {

        if (connection == null || !((ActiveMQConnection) connection).isStarted()) {
            LOG.info("ActiveMQ connection not wired!");
            return;
        }
        try {
            BufferedReader in = new BufferedReader
                    (new FileReader(filePath + "/" + filename + ".xml"));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                builder.append(line);
            }
            TextMessage testMessage = session.createTextMessage(builder.toString());
            MessageProducer producer = session.createProducer(outDestination);
            producer.send(testMessage);
            LOG.info("Message with user representation was send");
        } catch (JMSException e) {
            LOG.warn("Error while sending to queue", e);
        }
    }

    @PostConstruct
    private void init() {
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            goMessageListener();
        } catch (JMSException e) {
            if (e.getCause().getClass().equals(ConnectException.class)) {
                LOG.warn("ActiveMQ connection error!", e);
            } else {
                LOG.warn("Error while listening to queue", e);
            }
        }
    }

    @PreDestroy
    private void destroy() {
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException e) {
                LOG.error("Unexpected error!", e);
            }
        }
    }

    private void goMessageListener() throws JMSException {
        MessageConsumer consumer = session.createConsumer(inDestination);
        connection.start();
        consumer.setMessageListener(message -> {
            try {
                DiscountRequestObject dro = (DiscountRequestObject)
                        userMarshallingService.convertXMLStringToObject(((TextMessage) message).getText());
                userBusinessService.changeDiscount(dro.getLogin(), dro.getDiscount());
            } catch (JMSException | IOException e) {
                LOG.error("Unexpected error!", e);
            }
        });
    }
}
