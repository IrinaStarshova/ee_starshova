package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.access.repositories.UserRepository;
import com.accenture.flowershop.be.business.exceptions.UserExistException;
import com.accenture.flowershop.be.entity.user.Customer;
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
    public void addUser(Customer user)
            throws UserExistException {
        if (getUser(user.getLogin()) == null) {
            entityManager.persist(user);
        } else {
            throw new UserExistException();
        }
    }
}
