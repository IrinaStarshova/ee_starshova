package com.accenture.flowershop.be.access.cart;

import com.accenture.flowershop.be.access.flower.FlowerAccessService;
import com.accenture.flowershop.be.access.repositories.CartRepository;
import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.cart.QCart;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    private FlowerAccessService flowerAccessService;
    private static final Logger LOG = LoggerFactory.getLogger(CartAccessService.class);

    @Override
    public List<Cart> getCarts(String login) {
        return Lists.newArrayList(repository.findAll(QCart.cart.login.eq(login)));
    }

    @Override
    public Cart getCart(String login, Long flowerId) {
        return repository.findOne
                (QCart.cart.login.eq(login).
                        and(QCart.cart.flowerId.eq(flowerId))).orElse(null);
    }

    @Override
    @Transactional
    public void clearCart(List<Cart> carts) {
        for (Cart c : carts) {
            entityManager.remove(c);
        }
    }
}
