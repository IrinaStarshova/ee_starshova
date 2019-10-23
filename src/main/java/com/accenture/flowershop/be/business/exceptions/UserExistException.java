package com.accenture.flowershop.be.business.exceptions;

public class UserExistException extends Exception {
    public UserExistException() {
        super("Error creating account. Inputted login already exists!");
    }
}
