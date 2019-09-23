package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.Customer;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.util.List;

/**
 * Класс доступа к базе данных для заказов
 */
@Repository
public class OrderAccessServiceImpl implements OrderAccessService {

    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOG = 	LoggerFactory.getLogger(OrderAccessService.class);

    @Override
    @Transactional
    public void createOrder(Order order, String login, List<Cart> carts) {
        Customer customer = entityManager.getReference(Customer.class, login);
        order.setCost(customer.getCartCost());
        customer.addOrder(order);
        LOG.debug("Order: " + order.toString() + " was created!");
    }

    @Override
    public Order getOrder(Long orderId) {
        return entityManager.find(Order.class, orderId);
    }

    @Override
    public List<Order> getOrders() {
        TypedQuery<Order> o = entityManager.createQuery
                ("Select o from  Order o order by (o.creationDate, o.status)", Order.class);
        return o.getResultList();
    }

    @Override
    public List<Order> getOrders(String login) {
        TypedQuery<Order> q = entityManager.createQuery
                ("Select o from  Order o where o.login=:login", Order.class);
        q.setParameter("login", login);
        return q.getResultList();
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        entityManager.merge(order);
    }
}
