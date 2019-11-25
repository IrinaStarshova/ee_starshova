package com.accenture.flowershop.be.entity.user;

import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.fe.enums.user.UserRole;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Entity(name = "User")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "shop_user")
public class User {
    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public static final BigDecimal BALANCE = new BigDecimal(2000);
    public static final int DISCOUNT = 3;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "discount")
    private int discount;

    @Column(name = "cart_cost")
    private BigDecimal cartCost = BigDecimal.ZERO;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Cart> carts;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @OrderBy("creationDate DESC")
    private List<Order> orders;

    public User() {
    }

    public User(String login, String password, String firstName,
                String patronymic, String lastName, String address,
                String phoneNumber, BigDecimal balance, int discount) {
        this.login = login;
        this.password = password;
        this.role = UserRole.USER;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.discount = discount;
    }

    public void addCart(Cart cart) {
        carts.add(cart);
        cart.setCustomer(this);
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setCustomer(this);
    }

    public boolean isAdmin() {
        return role.equals(UserRole.ADMIN);
    }

    public void setCartCostWithDiscount(BigDecimal totalPrice) {
        BigDecimal d = new BigDecimal((100.00 - discount) / 100)
                .setScale(2, RoundingMode.HALF_UP);
        cartCost = (cartCost.add(totalPrice
                .multiply(d)))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public void increaseBalance(BigDecimal cost) {
        balance = balance.add(cost);
    }

    public void decreaseBalance(BigDecimal cost) {
        balance = balance.subtract(cost);
    }

    public void nullifyCartCost() {
        cartCost = BigDecimal.ZERO;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
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

    public List<Cart> getCarts() {
        return carts;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return super.toString() +
                " firstName: " + firstName +
                " patronymic: " + patronymic +
                " lastName: " + lastName +
                " address: " + address +
                " phoneNumber: " + phoneNumber +
                " balance: " + balance +
                " discount: " + discount;
    }
}
