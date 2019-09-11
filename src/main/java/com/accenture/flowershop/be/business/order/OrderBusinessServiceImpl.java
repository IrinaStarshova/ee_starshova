package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.cart.CartAccessService;
import com.accenture.flowershop.be.access.flower.FlowerAccessService;
import com.accenture.flowershop.be.access.order.OrderAccessService;
import com.accenture.flowershop.be.access.user.UserAccessService;
import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.Customer;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {
    @Autowired
    private OrderAccessService orderAccessService;
    @Autowired
    private FlowerAccessService flowerAccessService;
    @Autowired
    private CartAccessService cartAccessService;
    @Autowired
    private UserAccessService userAccessService;

    @Override
    public boolean createNewOrder(UserDTO user)
    {
        String login=user.getLogin();
        List<Cart> carts=cartAccessService.getCarts(login);
        if(carts.isEmpty())
            return false;
        Date date=new Date(System.currentTimeMillis());
        Order order=new Order(date,"created");
        orderAccessService.createOrder(order, login, carts);
        for(Cart c:carts) {
            Flower flower=flowerAccessService.getFlower(c.getFlowerId());
            flower.setQuantity(flower.getQuantity()-c.getQuantity());
            flower.setQuantityInCart(flower.getQuantityInCart() - c.getQuantity());
            flowerAccessService.updateFlower(flower);
        }
        cartAccessService.clearCartWhenOrdering(login);
        return true;
    }

    @Override
    public List<OrderDTO> getOrders(){
        List<OrderDTO> orderDTOs=new ArrayList<>();
        for(Order order:orderAccessService.getOrders())
            orderDTOs.add(new OrderDTO(order.getId(),order.getCost(),
                    order.getCreationDate(),order.getClosingDate(),order.getStatus()));
        return orderDTOs;
    }

    @Override
    public List<OrderDTO> getOrders(String login){
        List<OrderDTO> orderDTOs=new ArrayList<>();
        for(Order order:orderAccessService.getOrders(login))
            orderDTOs.add(new OrderDTO(order.getId(),order.getCost(),
                    order.getCreationDate(),order.getClosingDate(),order.getStatus()));
        return orderDTOs;
    }

    @Override
    public void closeOrder(Long id) {
        Order order=orderAccessService.getOrder(id);
        order.setClosingDate(new Date(System.currentTimeMillis()));
        order.setStatus("closed");
        orderAccessService.updateOrder(order);
    }
}
