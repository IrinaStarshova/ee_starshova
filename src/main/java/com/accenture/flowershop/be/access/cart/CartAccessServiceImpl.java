package com.accenture.flowershop.be.access.cart;

import com.accenture.flowershop.be.access.repositories.CartRepository;
import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.cart.QCart;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.user.Customer;
import com.google.common.collect.Lists;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CartRepository repository;
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
    public List<Cart> getCarts(String login) {
        return Lists.newArrayList(repository.findAll(QCart.cart.login.eq(login)));
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
    public boolean clearCart(String login){
        List<Cart> carts=getCarts(login);
        if(carts.isEmpty())
            return false;
        for (Cart c : carts) {
            entityManager.remove(c);
            Flower flower = entityManager.getReference(Flower.class, c.getFlowerId());
            flower.setQuantityInCart(flower.getQuantityInCart() - c.getQuantity());
        }
        return true;
    }
}
