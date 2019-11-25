package com.accenture.flowershop.be.access.cart;

import com.accenture.flowershop.be.access.repositories.CartRepository;
import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.cart.QCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @Override
    public List<Cart> getCarts(String login) {
        return (List<Cart>) repository.findAll(QCart.cart.customer.login.eq(login));
    }

    @Override
    public Cart getCart(String login, Long flowerId) {
        return repository.findOne
                (QCart.cart.customer.login.eq(login).
                        and(QCart.cart.flower.id.eq(flowerId))).orElse(null);
    }

    @Override
    @Transactional
    public void clearCart(List<Cart> carts) {
        carts.forEach(cart -> entityManager.remove(cart));
    }
}
