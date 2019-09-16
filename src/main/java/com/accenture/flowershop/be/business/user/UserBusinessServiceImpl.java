package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.business.UserMarshallingService;
import com.accenture.flowershop.be.access.cart.CartAccessService;
import com.accenture.flowershop.be.access.order.OrderAccessService;
import com.accenture.flowershop.be.access.user.UserAccessService;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.Customer;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.enums.order.OrderStatuses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public boolean createNewUser(String username, String password,
                                 String firstName, String patronymic,
                                 String lastName, String address,
                                 String phoneNumber) throws IOException {
        if(userAccessService.getUser(username)!=null)
            return false;
        Customer customer=new Customer(username, password,
                firstName,  patronymic, lastName,  address,
                phoneNumber, Customer.BALANCE, Customer.DISCOUNT);
        userAccessService.addUser(customer);
        userMarshallingService.convertFromUserToXML(customer,customer.getLogin());
        return true;
    }

    @Override
    public UserDTO userLogin(String login, String password)
    {
        User user=userAccessService.getUser(login);
        if(user==null)
            return null;
        if(!user.getPassword().equals(password))
            return null;
        if(user.isAdmin())
            return new UserDTO(user.getLogin(),true);
        Customer customer=(Customer)user;
        return userToUserDTO(customer);
    }

    @Override
    public boolean payOrder(String login,Long orderId){
        Customer customer=(Customer)userAccessService.getUser(login);
        Order order=orderAccessService.getOrder(orderId);
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
    public UserDTO getUser(String login) {
        Customer customer=(Customer)userAccessService.getUser(login);
        if(customer==null)
            return null;
        return userToUserDTO(customer);
    }

    private UserDTO userToUserDTO(Customer customer) {
        return new UserDTO(customer.getLogin(), customer.getFirstName(),
                customer.getPatronymic(),customer.getLastName(), customer.getAddress(),
                customer.getPhoneNumber(), customer.getBalance(), customer.getDiscount(),
                customer.getCartCost(), false);
    }
}
