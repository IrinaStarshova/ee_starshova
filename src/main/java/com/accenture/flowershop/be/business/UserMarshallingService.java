package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.user.User;

import java.io.IOException;

public interface UserMarshallingService {
    void convertFromUserToXML(User user, String filename) throws IOException;
}
