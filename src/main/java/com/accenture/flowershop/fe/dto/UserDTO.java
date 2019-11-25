package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.fe.enums.user.UserRole;

import java.math.BigDecimal;
import java.util.List;

public class UserDTO {
    private String login;
    private String firstName;
    private UserRole role;
    private String patronymic;
    private String lastName;
    private String address;
    private String phoneNumber;
    private BigDecimal balance;
    private int discount;
    private BigDecimal cartCost;
    private List<CartDTO> carts;
    private List<OrderDTO> orders;

    public String getLogin() {
        return login;
    }

    public UserRole getRole() {
        return role;
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

    public List<CartDTO> getCarts() {
        return carts;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(UserRole role) {
        this.role = role;
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

    public void setCarts(List<CartDTO> carts) {
        this.carts = carts;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public boolean isAdmin() {
        return role.equals(UserRole.ADMIN);
    }
}
