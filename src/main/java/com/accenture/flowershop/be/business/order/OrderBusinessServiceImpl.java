package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.order.OrderAccessService;
import com.accenture.flowershop.be.access.user.UserAccessService;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.enums.order.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {
    @Autowired
    private OrderAccessService orderAccessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private UserAccessService userAccessService;

    private static final Logger LOG = LoggerFactory.getLogger(OrderBusinessService.class);

    @Override
    @Transactional
    public void createNewOrder(String login) {
        User customer = userAccessService.getCustomer(login);
        List<Cart> carts = customer.getCarts();
        carts.forEach(cart -> {
            flowerBusinessService.decreaseQuantityOfFlower
                    (cart.getFlower(), cart.getQuantity());
            cart.setCustomer(null);
        });
        Order order = new Order(customer.getCartCost(),
                new Timestamp(System.currentTimeMillis()),
                OrderStatus.CREATED, new ArrayList<>(carts));
        customer.nullifyCartCost();
        customer.addOrder(order);
        LOG.info("Order: " + order.toString() + " was created!");
    }

    @Override
    @Transactional
    public Page<Order> getOrders(int pageNumber, int quantity) {
        Page<Order> ordersPage = orderAccessService.getOrders(pageNumber, quantity);
        initializeCarts(ordersPage.getContent());
        return ordersPage;
    }

    private void initializeCarts(List<Order> orders) {
        orders.forEach(order -> order.getCarts()
                .forEach(cart -> cart.getFlower().getName()));
    }

    @Override
    @Transactional
    public boolean payOrder(Long orderId) {
        Order order = orderAccessService.getOrder(orderId);
        User customer = order.getCustomer();
        BigDecimal orderCost = order.getCost();
        if (orderCost.compareTo(customer.getBalance()) > 0) {
            return false;
        }
        customer.decreaseBalance(orderCost);
        order.pay();
        return true;
    }

    @Override
    @Transactional
    public void closeOrder(Long id) {
        Order order = orderAccessService.getOrder(id);
        order.close();
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        Order order = orderAccessService.getOrder(id);
        if (!order.isClosed()) {
            order.getCarts().forEach(cart -> flowerBusinessService
                    .increaseQuantityOfFlower(cart.getFlower(), cart.getQuantity()));
            if (order.isPaid()) {
                order.getCustomer().increaseBalance(order.getCost());
            }
            orderAccessService.deleteOrder(order);
            LOG.info("Order: " + order.toString() + " was deleted!");
        }
    }
}
