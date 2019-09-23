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
    private boolean admin=false;

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setCartCost(BigDecimal cartCost) {
        this.cartCost = cartCost;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }
}
