package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.access.repositories.UserRepository;
import com.accenture.flowershop.be.business.exceptions.CreateUserException;
import com.accenture.flowershop.be.entity.user.QUser;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Класс доступа к базе данных для пользователей
 */
@Repository
public class UserAccessServiceImpl implements UserAccessService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository repository;

    @Override
    @Transactional
    public User getUser(String login) {
        return repository.findOne(QUser.user.login.eq(login)).orElse(null);
    }

    @Override
    @Transactional
    public User getCustomer(String login) {
        return repository.findOne(QUser.user.login.eq(login))
                .orElseThrow(() -> new RuntimeException("Customer not found by login"));
    }

    @Override
    @Transactional
    public void addUser(User user)
            throws CreateUserException {
        if (getUser(user.getLogin()) == null) {
            entityManager.persist(user);
        } else {
            throw new CreateUserException(CreateUserException.USER_EXIST_MESSAGE);
        }
    }
}
