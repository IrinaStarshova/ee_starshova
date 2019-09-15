package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

public class UserDTO {
    private String login;
    private String firstName;
    private String patronymic;
    private String lastName;
    private String address;
    private String phoneNumber;
    private BigDecimal balance;
    private int discount;
    private BigDecimal cartCost;
    private boolean admin;

    public UserDTO(String login, String firstName, String patronymic,
                   String lastName, String address, String phoneNumber,
                   BigDecimal balance, int discount, BigDecimal cartCost,
                   boolean admin){
        this.login=login;
        this.firstName=firstName;
        this.patronymic=patronymic;
        this.lastName=lastName;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.balance=balance;
        this.discount=discount;
        this.cartCost=cartCost;
        this.admin=admin;
    }
    public UserDTO(String login, boolean admin){
        this.login=login;
        this.admin=admin;
    }

    public String getLogin() {
        return login;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public int getDiscount() {
        return discount;
    }

    public BigDecimal getCartCost() {
        return cartCost;
    }


    public boolean isAdmin() {
        return admin;
    }
}
