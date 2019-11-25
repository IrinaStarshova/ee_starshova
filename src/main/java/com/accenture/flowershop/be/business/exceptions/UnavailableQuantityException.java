package com.accenture.flowershop.be.business.exceptions;

public class UnavailableQuantityException extends RuntimeException {
    public UnavailableQuantityException() {
        super("Error creating order. The selected number of flowers is not available!");
    }
}
