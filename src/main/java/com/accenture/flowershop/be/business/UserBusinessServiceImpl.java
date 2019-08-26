package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.access.UserAccessService;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {
    @Autowired
    private UserAccessService userAccessService;

    public boolean createNewUser(User user)
    {
        return userAccessService.addUser(user);
    }
    public boolean userLogin(User user)
    {
        return userAccessService.login(user);
    }
}
