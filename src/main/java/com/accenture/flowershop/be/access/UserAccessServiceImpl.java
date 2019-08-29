package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Service
public class UserAccessServiceImpl implements UserAccessService {

    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOG = 	LoggerFactory.getLogger(UserAccessService.class);

    public boolean login(User user) {
        User u=entityManager.find(User.class,user.getLogin());
        return (u != null) && u.getPassword().equals(user.getPassword());
    }
    @Transactional
    public boolean addUser(User user) {
        if(entityManager.find(User.class,user.getLogin())!=null)
            return false;
        entityManager.persist(user);
        LOG.debug("User with name = {} was created!",user.getLogin());
        return true;
    }
}
