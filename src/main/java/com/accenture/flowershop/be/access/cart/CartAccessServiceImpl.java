package com.accenture.flowershop.be.access.cart;

import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.Customer;
import org.slf4j.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Класс доступа к базе данных для корзины
 */
@Service
public class CartAccessServiceImpl implements CartAccessService {

    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOG = 	LoggerFactory.getLogger(CartAccessService.class);

    @Override
    @Transactional
    public BigDecimal addCartItem(Cart cart, String login) {
        Customer customer = entityManager.getReference(Customer.class,login);
        customer.addCart(cart);
        customer.setCartCostWithDiscount(cart.getTotalPrice());
        LOG.debug("Add cart item to cart of user - " + customer.getLogin() + ": " + cart.toString());
        return customer.getCartCost();
    }

    @Override
    @Transactional
    public List<Cart> getCarts(String login) {
        Customer customer = entityManager.getReference(Customer.class, login);
        return customer.getCarts();
    }

    @Override
    @Transactional
    public List<Cart> getCarts(Long orderId) {
        Order order = entityManager.getReference(Order.class, orderId);
        return order.getCarts();
    }

    @Override
    @Transactional
    public void clearCartWhenOrdering(String login) {
        Customer customer = entityManager.getReference(Customer.class, login);
        customer.clearCart();
        customer.setCartCost(BigDecimal.ZERO);
    }

    @Override
    @Transactional
    public void clearCart(String login){
        Customer customer = entityManager.getReference(Customer.class, login);
        List<Cart> carts=customer.getCarts();
        if(!carts.isEmpty()) {
            for (Cart c : carts) {
                entityManager.remove(c);
                Flower flower = entityManager.getReference(Flower.class, c.getFlowerId());
                flower.setQuantityInCart(flower.getQuantityInCart() - c.getQuantity());
            }
        }
        customer.setCartCost(BigDecimal.ZERO);
        customer.clearCart();
    }
}
