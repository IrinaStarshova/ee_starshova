package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.access.UserAccessService;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {
    @Autowired
    private UserAccessService userAccessService;

    public boolean createNewUser(String name, String password)
    {
        return userAccessService.addUser(new User(name, password));
    }
    public boolean userLogin(String name, String password)
    {
        return userAccessService.login(new User(name, password));
    }
}
