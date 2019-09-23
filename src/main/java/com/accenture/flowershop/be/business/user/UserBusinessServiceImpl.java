package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.business.UserMarshallingService;
import com.accenture.flowershop.be.access.cart.CartAccessService;
import com.accenture.flowershop.be.access.order.OrderAccessService;
import com.accenture.flowershop.be.access.user.UserAccessService;
import com.accenture.flowershop.be.business.jms.MessageService;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.Customer;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.enums.order.OrderStatuses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jms.JMSException;
import java.io.IOException;
import java.math.BigDecimal;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private UserAccessService userAccessService;
    @Autowired
    private OrderAccessService orderAccessService;
    @Autowired
    private CartAccessService cartAccessService;
    @Autowired
    private UserMarshallingService userMarshallingService;
    @Autowired
    private MessageService messageService;

    @Override
    public void createNewUser(String username, String password,
                                 String firstName, String patronymic,
                                 String lastName, String address,
                                 String phoneNumber) throws IOException, JMSException {
        Customer customer=new Customer(username, password,
                firstName,  patronymic, lastName,  address,
                phoneNumber, Customer.BALANCE, Customer.DISCOUNT);
        userAccessService.addUser(customer);
        userMarshallingService.convertFromUserToXML(customer,customer.getLogin());
        messageService.sendCustomerXML(customer.getLogin());
    }

    @Override
    public boolean isUserExists(String login){
        return userAccessService.getUser(login) != null;
    }

    @Override
    public User userLogin(String login, String password)
    {
        User user=userAccessService.getUser(login);
        if(user==null)
            return null;
        if(!user.getPassword().equals(password))
            return null;
        return user;
    }

    @Override
    public boolean payOrder(String login,Long orderId){
        Customer customer=(Customer)userAccessService.getUser(login);
        System.out.println("");
        Order order=orderAccessService.getOrder(orderId);
        System.out.println("");
        BigDecimal orderCost=order.getCost();
        BigDecimal userBalance=customer.getBalance();
        if(orderCost.compareTo(userBalance) > 0)
            return false;
        customer.setBalance(userBalance.subtract(orderCost));
        order.setStatus(OrderStatuses.paid);
        userAccessService.updateCustomer(customer);
        orderAccessService.updateOrder(order);
        return true;
    }

    @Override
    public Customer getCustomer(String login) {
        return (Customer)userAccessService.getUser(login);
    }
}
