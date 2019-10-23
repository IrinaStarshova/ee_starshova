package com.accenture.flowershop.be.business.jms;

import com.accenture.flowershop.be.business.UserMarshallingService;
import com.accenture.flowershop.be.entity.user.Customer;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.jms.*;
import java.io.IOException;

public class SendDiscountObj_forTest {
    private UserMarshallingService userMarshallingService;
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;
    private MessageProducer producer;
    private static final Logger LOG = 	LoggerFactory.getLogger(SendDiscountObj_forTest.class);

    public SendDiscountObj_forTest() throws JMSException {
        ApplicationContext context = new
                    ClassPathXmlApplicationContext("classpath*:/config/context_for_jms.xml");
        userMarshallingService=(UserMarshallingService) context.getBean("XMLConverter");
        ConnectionFactory connectionFactory=
                new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Destination outDestination = session.createQueue("OUT_QUEUE");
        consumer = session.createConsumer(outDestination);
        Destination inDestination = session.createQueue("IN_QUEUE");
        producer = session.createProducer(inDestination);
    }

    public static void main(String[] args) throws JMSException {
        new SendDiscountObj_forTest().sendDiscountObj_forTestChange();
    }
    public  void sendDiscountObj_forTestChange() throws JMSException {

        connection.start();
        consumer.setMessageListener(message -> {
            try {
                Customer customer = (Customer) userMarshallingService.
                        convertXMLStringToObject(((TextMessage) message).getText());
                TextMessage droXML =
                        session.createTextMessage(userMarshallingService.
                                convertObjectToXMLString(new DiscountRequestObject
                                        (customer.getLogin(),20)));
                producer.send(droXML);
                LOG.info("Message with discount for user \"" + customer.getLogin() + "\" was send");
            } catch (JMSException | IOException e) {
                e.printStackTrace();
            }
        });
    }
}
