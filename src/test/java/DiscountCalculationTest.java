import com.accenture.flowershop.be.entity.user.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountCalculationTest {
    private Customer customer;
    private BigDecimal cartCost;
    private BigDecimal expectedResult;

    @Before
    public void initialize() {
        customer=new Customer();
        customer.setDiscount(15);
        customer.setCartCost(new BigDecimal(15.46).setScale(2, RoundingMode.HALF_UP));
        cartCost=new BigDecimal(100);
        expectedResult=new BigDecimal(100.46).setScale(2, RoundingMode.HALF_UP);
    }

    @Test
    public void testDiscountCalculation() {
        customer.setCartCostWithDiscount(cartCost);
        Assert.assertEquals(expectedResult,customer.getCartCost());
    }
}


