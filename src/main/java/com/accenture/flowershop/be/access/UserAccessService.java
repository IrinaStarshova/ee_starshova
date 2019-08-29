package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.user.User;

public interface UserAccessService {
    boolean login(User user);
    boolean  addUser(User user);
}
