package com.accenture.flowershop.be.business;

public interface UserBusinessService {

    boolean createNewUser(String name, String password);
    boolean userLogin(String name, String password);
}