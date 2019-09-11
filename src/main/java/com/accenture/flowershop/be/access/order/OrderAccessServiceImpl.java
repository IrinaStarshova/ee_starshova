package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.Customer;
import org.slf4j.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Класс доступа к базе данных для заказов
 */
@Service
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
        for (Cart c:carts){
            entityManager.merge(c);
            order.addCart(c);
        }
        LOG.debug("Order: " + order.toString() + " was created!");
    }

    @Override
    @Transactional
    public Order getOrder(Long orderId) {
        return entityManager.find(Order.class, orderId);
    }

    @Override
    public List<Order> getOrders() {
        TypedQuery<Order> o = entityManager.createQuery("Select o from  Order o", Order.class);
        return o.getResultList();
    }

    @Override
    @Transactional
    public List<Order> getOrders(String login) {
        Customer customer = entityManager.getReference(Customer.class, login);
        return customer.getOrders();
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        entityManager.merge(order);
    }
}
