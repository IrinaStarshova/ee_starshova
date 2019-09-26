import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class RestServiceIT {
    private static String validLogin;
    private static String invalidLogin;
    private static RestTemplate restTemplate;
    static {
        validLogin= "Harry";
        invalidLogin= "admin";
        restTemplate = new RestTemplate();
    }

    @Test
    public void testValidLogin() {
        Assert.assertFalse(testLoginValidation(validLogin));
    }

    @Test
    public void testInvalidLogin(){
        Assert.assertTrue(testLoginValidation(invalidLogin));
    }

    private boolean testLoginValidation(String login){
        return restTemplate.
                getForObject("http://localhost:8080/flowershop/rest/verifyLogin/"+login,
                        Boolean.class);
    }
}


