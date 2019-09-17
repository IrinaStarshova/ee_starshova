package com.accenture.flowershop.be.access.cart;

import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.user.Customer;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Класс доступа к базе данных для корзины
 */
@Repository
public class CartAccessServiceImpl implements CartAccessService {

    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOG = 	LoggerFactory.getLogger(CartAccessService.class);

    @Override
    @Transactional
    public void addCartItem(Cart cart, String login) {
        Customer customer = entityManager.getReference(Customer.class,login);
        customer.addCart(cart);
        customer.setCartCostWithDiscount(cart.getTotalPrice());
        LOG.debug("Add cart item to cart of user - " + customer.getLogin() + ": " + cart.toString());
    }

    @Override
    @Transactional
    public List<Cart> getCarts(String login) {
        TypedQuery<Cart> q = entityManager.createQuery
                ("Select c from  Cart c where c.login=:login", Cart.class);
        q.setParameter("login", login);
        return  q.getResultList();
    }

    @Override
    @Transactional
    public List<Cart> getCarts(Long orderId) {
        TypedQuery<Cart> q = entityManager.createQuery
                ("Select c from  Cart c where c.orderId=:orderId", Cart.class);
        q.setParameter("orderId", orderId);
        return  q.getResultList();
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
        List<Cart> carts=getCarts(login);
        if(!carts.isEmpty()) {
            for (Cart c : carts) {
                entityManager.remove(c);
                Flower flower = entityManager.getReference(Flower.class, c.getFlowerId());
                flower.setQuantityInCart(flower.getQuantityInCart() - c.getQuantity());
            }
        }
    }
}
