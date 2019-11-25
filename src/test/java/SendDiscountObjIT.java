import com.accenture.flowershop.be.business.UserMarshallingService;
import com.accenture.flowershop.be.business.jms.DiscountRequestObject;
import com.accenture.flowershop.be.entity.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.*;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/contextForTest.xml")
public class SendDiscountObjIT {

    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    @Qualifier("outDestination")
    private Destination outDestination;
    @Autowired
    @Qualifier("inDestination")
    private Destination inDestination;
    @Autowired
    private UserMarshallingService userMarshallingService;

    private static final Logger LOG = LoggerFactory.getLogger(SendDiscountObjIT.class);

    private Connection connection;
    private Session session;
    private MessageConsumer consumer;
    private MessageProducer producer;

    @Before
    public void init() throws JMSException {
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        consumer = session.createConsumer(outDestination);
        producer = session.createProducer(inDestination);
    }

    @Test
    public void sendDiscountObj() throws JMSException, IOException {
        if (connection != null) {
            try {
                connection.start();
                User customer = (User) userMarshallingService.
                        convertXMLStringToObject(((TextMessage) consumer.receive()).getText());
                TextMessage droXML =
                        session.createTextMessage(userMarshallingService.
                                convertObjectToXMLString(new DiscountRequestObject
                                        (customer.getLogin(), 20)));
                producer.send(droXML);
                LOG.info("Message with discount for user \"" + customer.getLogin() + "\" was send");
            } finally {
                try {
                    connection.close();
                } catch (JMSException e) {
                    LOG.error("Unexpected error!", e);
                }
            }
        }

    }
}


