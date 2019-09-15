package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.cart.CartAccessService;
import com.accenture.flowershop.be.access.order.OrderAccessService;
import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.fe.dto.CartDTO;
import com.accenture.flowershop.fe.dto.OrderDTO;
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

    @Override
    public boolean createNewOrder(String login)
    {
        List<Cart> carts=cartAccessService.getCarts(login);
        if(carts.isEmpty())
            return false;
        Date date=new Date(System.currentTimeMillis());
        Order order=new Order(date,OrderStatuses.created);
        orderAccessService.createOrder(order, login, carts);
        cartAccessService.clearCartWhenOrdering(login);
        return true;
    }

    @Override
    public List<OrderDTO> getOrders(){
        return ordersToOrderDTOs(orderAccessService.getOrders());
    }

    @Override
    public List<OrderDTO> getOrders(String login){
        return ordersToOrderDTOs(orderAccessService.getOrders(login));
    }

    @Override
    public void closeOrder(Long id) {
        Order order=orderAccessService.getOrder(id);
        order.setClosingDate(new Date(System.currentTimeMillis()));
        order.setStatus(OrderStatuses.closed);
        orderAccessService.updateOrder(order);
    }

    private List<OrderDTO> ordersToOrderDTOs(List<Order> orders) {
        List<OrderDTO> orderDTOs=new ArrayList<>();
        for(Order order:orders) {
            OrderDTO orderDTO=new OrderDTO(order.getId(), order.getCost(),
                    order.getCreationDate(), order.getClosingDate(), order.getStatus());
            orderDTOs.add(orderDTO);
            addCartToOrderDTO(cartAccessService.getCarts(order.getId()),orderDTO);
        }
        return orderDTOs;
    }
    private void addCartToOrderDTO(List<Cart> cartList, OrderDTO orderDTO){
        for (Cart cart : cartList)
            orderDTO.addCart(new CartDTO(cart.getId(), cart.getFlowerName(),
                    cart.getQuantity(), cart.getTotalPrice()));
    }
}
