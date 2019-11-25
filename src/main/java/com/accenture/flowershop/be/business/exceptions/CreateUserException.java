package com.accenture.flowershop.be.business.exceptions;

public class CreateUserException extends Exception {
    public static final String USER_EXIST_MESSAGE = "Error creating account. Inputted login already exists!";
    public static final String INTERNAL_ERROR = "Internal error. Try again";

    public CreateUserException(String message) {
        super(message);
    }
}
