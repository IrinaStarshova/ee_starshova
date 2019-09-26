package com.accenture.flowershop.fe.enums.order;

public enum OrderStatus {
    CREATED,
    PAID,
    CLOSED;

    public String toString(){
        return this.name().toLowerCase();
    }
}
