package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.user.User;

public interface UserBusinessService {

    boolean createNewUser(User user);
    boolean userLogin(User user);
}