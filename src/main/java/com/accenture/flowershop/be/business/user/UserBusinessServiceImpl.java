package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.order.OrderAccessService;
import com.accenture.flowershop.be.access.user.UserAccessService;
import com.accenture.flowershop.be.business.UserMarshallingService;
import com.accenture.flowershop.be.business.exceptions.UserExistException;
import com.accenture.flowershop.be.business.jms.MessageService;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.Customer;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.enums.order.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private UserAccessService userAccessService;
    @Autowired
    private OrderAccessService orderAccessService;
    @Autowired
    private UserMarshallingService userMarshallingService;
    @Autowired
    private MessageService messageService;
    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessService.class);

    @Override
    public void createNewUser(String username, String password,
                              String firstName, String patronymic,
                              String lastName, String address,
                              String phoneNumber)
            throws UserExistException {
        Customer customer = new Customer(username, password,
                firstName, patronymic, lastName, address,
                phoneNumber, Customer.BALANCE, Customer.DISCOUNT);
        try {
            userAccessService.addUser(customer);
            userMarshallingService.convertFromUserToXML(customer, customer.getLogin());
            messageService.sendCustomerXML(customer.getLogin());
            LOG.info("User with login: {} was created", username);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataIntegrityViolationException e) {
            throw new UserExistException();
        }
    }

    @Override
    public boolean isUserExists(String login) {
        return userAccessService.getUser(login) != null;
    }

    @Override
    @Transactional
    public User userLogin(String login, String password) {
        User user = userAccessService.getUser(login);
        if (user == null)
            return null;
        if (!user.getPassword().equals(password))
            return null;
        if (!user.isAdmin()) {
            initializeCarts((Customer) user);
            initializeOrders((Customer) user);
        }
        return user;
    }

    @Override
    @Transactional
    public boolean payOrder(String login, Long orderId) {
        Customer customer = (Customer) userAccessService.getUser(login);
        Order order = orderAccessService.getOrder(orderId);
        BigDecimal orderCost = order.getCost();
        BigDecimal userBalance = customer.getBalance();
        if (orderCost.compareTo(userBalance) > 0)
            return false;
        customer.setBalance(userBalance.subtract(orderCost));
        order.setStatus(OrderStatus.PAID);
        return true;
    }

    @Override
    @Transactional
    public void changeDiscount(String login, int discount) {
        Customer customer = (Customer) userAccessService.getUser(login);
        if (customer != null)
            customer.setDiscount(discount);
    }

    @Override
    @Transactional
    public Customer getCustomer(String login) {
        Customer customer = (Customer) userAccessService.getUser(login);
        initializeCarts(customer);
        initializeOrders(customer);
        return customer;
    }

    /**
     * Метод для загрузки корзины пользователя
     *
     * @param customer - сущность пользователя
     */
    private void initializeCarts(Customer customer) {
        customer.getCarts().size();
    }

    /**
     * Метод для загрузки заказов пользователя
     *
     * @param customer - сущность пользователя
     */
    private void initializeOrders(Customer customer) {
        for (Order o : customer.getOrders())
            o.getCarts().size();
    }
}
