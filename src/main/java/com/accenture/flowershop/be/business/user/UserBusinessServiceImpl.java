package com.accenture.flowershop.be.business.user;

import com.accenture.flowershop.be.access.user.UserAccessService;
import com.accenture.flowershop.be.business.UserMarshallingService;
import com.accenture.flowershop.be.business.exceptions.CreateUserException;
import com.accenture.flowershop.be.business.jms.MessageService;
import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private UserAccessService userAccessService;
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
            throws CreateUserException {
        User customer = new User(username, password,
                firstName, patronymic, lastName, address,
                phoneNumber, User.BALANCE, User.DISCOUNT);
        try {
            userAccessService.addUser(customer);
            LOG.info("User with login: \"{}\" was created", username);
            userMarshallingService.convertFromUserToXML(customer, customer.getLogin());
            messageService.sendCustomerXML(customer.getLogin());
        } catch (IOException e) {
            LOG.error("Unexpected error!", e);
        } catch (DataIntegrityViolationException e) {
            LOG.error("Unexpected error!", e);
            throw new CreateUserException(CreateUserException.INTERNAL_ERROR);
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
        if (user == null) {
            return null;
        }
        if (!user.getPassword().equals(password)) {
            return null;
        }
        if (!user.isAdmin()) {
            initializeCarts(user.getCarts());
            initializeOrders(user.getOrders());
        }
        return user;
    }

    @Override
    @Transactional
    public void changeDiscount(String login, int discount) {
        User user = userAccessService.getUser(login);
        if (user != null) {
            user.setDiscount(discount);
            LOG.info("Discount of user \"{}\" changed", login);
        }
    }

    @Override
    @Transactional
    public User getCustomer(String login) {
        User customer = userAccessService.getCustomer(login);
        initializeCarts(customer.getCarts());
        initializeOrders(customer.getOrders());
        return customer;
    }

    /**
     * Метод для загрузки корзины пользователя
     *
     * @param carts - список элементов корзины
     */
    private void initializeCarts(List<Cart> carts) {
        carts.forEach(cart -> cart.getFlower().getName());
    }

    /**
     * Метод для загрузки заказов пользователя
     *
     * @param orders - список заказов
     */
    private void initializeOrders(List<Order> orders) {
        orders.forEach(order -> initializeCarts(order.getCarts()));
    }
}
