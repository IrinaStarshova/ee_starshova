package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.cart.CartAccessService;
import com.accenture.flowershop.be.access.order.OrderAccessService;
import com.accenture.flowershop.be.access.user.UserAccessService;
import com.accenture.flowershop.be.business.exceptions.UnavailableQuantityException;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.Customer;
import com.accenture.flowershop.fe.enums.order.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {
    @Autowired
    private OrderAccessService orderAccessService;
    @Autowired
    private CartAccessService cartAccessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private UserAccessService userAccessService;
    private static final Logger LOG = LoggerFactory.getLogger(OrderBusinessService.class);

    @Override
    @Transactional
    public void createNewOrder(String login)
            throws ObjectOptimisticLockingFailureException,
            UnavailableQuantityException {
        Customer customer = (Customer) userAccessService.getUser(login);
        List<Cart> carts = customer.getCarts();
        for (Cart c : carts) {
            flowerBusinessService.decreaseQuantityOfFlower(c.getFlowerId(), c.getQuantity());
        }
        Date date = new Date(System.currentTimeMillis());
        Order order = new Order(customer.getCartCost(), date,
                OrderStatus.CREATED, new ArrayList<>(carts));
        customer.clearCart();
        customer.setCartCost(BigDecimal.ZERO);
        customer.addOrder(order);
        LOG.info("Order: " + order.toString() + " was created!");
    }

    @Override
    @Transactional
    public List<Order> getOrders() {
        return initializeCarts(orderAccessService.getOrders());
    }

    private List<Order> initializeCarts(List<Order> orders) {
        for (Order o : orders)
            o.getCarts().size();
        return orders;
    }

    @Override
    @Transactional
    public void closeOrder(Long id) {
        Order order = orderAccessService.getOrder(id);
        order.setClosingDate(new Date(System.currentTimeMillis()));
        order.setStatus(OrderStatus.CLOSED);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id, String login)
            throws ObjectOptimisticLockingFailureException {
        Order order = orderAccessService.getOrder(id);
        if (order.getStatus() != OrderStatus.CLOSED) {
            for (Cart c : order.getCarts()) {
                flowerBusinessService.increaseQuantityOfFlower(c.getFlowerId(), c.getQuantity());
            }
            if (order.getStatus() == OrderStatus.PAID) {
                Customer customer = (Customer) userAccessService.getUser(login);
                customer.setBalance(customer.getBalance().add(order.getCost()));
            }
            orderAccessService.deleteOrder(order);
            LOG.info("Order: " + order.toString() + " was deleted!");
        }
    }
}
