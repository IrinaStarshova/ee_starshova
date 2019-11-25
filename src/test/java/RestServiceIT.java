import com.accenture.flowershop.be.entity.order.Order;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.web.client.RestTemplate;

public class RestServiceIT {
    private static String validLogin;
    private static String invalidLogin;
    private static RestTemplate restTemplate;

    static {
        validLogin = "Harry";
        invalidLogin = "admin";
        restTemplate = new RestTemplate();
    }

    @Test
    public void testValidLogin() {
        Assert.assertFalse(testLoginValidation(validLogin));
    }

    @Test
    public void testInvalidLogin() {
        Assert.assertTrue(testLoginValidation(invalidLogin));
    }

    private boolean testLoginValidation(String login) {
        return restTemplate.
                getForObject("http://localhost:8080/flowershop/rest/verifyLogin/" + login,
                        Boolean.class);
    }

    @Test
    public void testOrders() {
        String result= restTemplate.getForObject
                ("http://localhost:8080/flowershop/rest/order/" + 0 + "/" + 2, String.class);
        System.out.println(result);
    }
}


