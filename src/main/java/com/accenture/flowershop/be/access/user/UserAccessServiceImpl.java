package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.Customer;
import com.accenture.flowershop.be.entity.user.User;
import org.slf4j.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Класс доступа к базе данных для пользователей
 */
@Service
public class UserAccessServiceImpl implements UserAccessService {

    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOG = 	LoggerFactory.getLogger(UserAccessService.class);

    public User findUser(String login, String password) {
        User foundUser=entityManager.find(User.class,login);
        if((foundUser != null) && foundUser.getPassword().equals(password))
            return foundUser;
        return null;
    }

    @Override
    @Transactional
    public User getUser(String login) {
           return entityManager.find(User.class,login);
    }

    @Override
    @Transactional
    public void addUser(Customer user) {
        entityManager.persist(user);
        LOG.debug("User with login = {} was created: " + user.toString(),user.getLogin());
    }

    @Override
    @Transactional
    public void updateCustomer(Customer customer){
        entityManager.merge(customer);
    }
}
