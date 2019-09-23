package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.cart.CartAccessService;
import com.accenture.flowershop.be.access.flower.FlowerAccessService;
import com.accenture.flowershop.be.access.order.OrderAccessService;
import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.fe.enums.order.OrderStatuses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.*;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {
    @Autowired
    private OrderAccessService orderAccessService;
    @Autowired
    private CartAccessService cartAccessService;
    @Autowired
    private FlowerAccessService flowerAccessService;

    @Override
    public boolean createNewOrder(String login)
    {
        List<Cart> carts=cartAccessService.getCarts(login);
        if(carts.isEmpty())
            return false;
        Date date=new Date(System.currentTimeMillis());
        Order order=new Order(date,OrderStatuses.created);
        order.setCarts(carts);
        orderAccessService.createOrder(order, login, carts);
        for (Cart c:carts){
            flowerAccessService.setQuantity(c.getFlowerId(),c.getQuantity());
        }
        cartAccessService.clearCartWhenOrdering(login);
        return true;
    }

    @Override
    public List<Order> getOrders(){
        return orderAccessService.getOrders();
    }

    @Override
    public List<Order> getOrders(String login){
        return orderAccessService.getOrders(login);
    }

    @Override
    public void closeOrder(Long id) {
        Order order=orderAccessService.getOrder(id);
        order.setClosingDate(new Date(System.currentTimeMillis()));
        order.setStatus(OrderStatuses.closed);
        orderAccessService.updateOrder(order);
    }
}
