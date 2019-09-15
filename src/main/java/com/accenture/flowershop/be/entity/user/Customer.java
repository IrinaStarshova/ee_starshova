package com.accenture.flowershop.be.entity.user;

import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.be.entity.order.Order;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Entity(name = "Customer")
@Table(name = "customers")
public class Customer extends User {
    public static final BigDecimal BALANCE=new BigDecimal(2000);
    public static final int DISCOUNT=3;
    @Column(name="firstName")
    private String firstName;

    @Column(name="patronymic")
    private String patronymic;

    @Column(name="lastName")
    private String lastName;

    @Column(name="address")
    private String address;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="balance")
    private BigDecimal balance;

    @Column(name="discount")
    private int discount;

    @Column(name="cartCost")
    private BigDecimal cartCost=BigDecimal.ZERO;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "login")
    private List<Cart> carts;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "login")
    private List<Order> orders;

    public Customer(){}
    public Customer(String login, String password, String firstName,
             String patronymic, String lastName, String address,
             String phoneNumber, BigDecimal balance, int discount){
        super(login,password);
        this.firstName=firstName;
        this.patronymic=patronymic;
        this.lastName=lastName;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.balance=balance;
        this.discount=discount;
    }

    public void addCart(Cart cart){
        Cart findCart=findByFlowerName(cart.getFlowerName());
        if(findCart!=null) {
            findCart.setQuantity(findCart.getQuantity() + cart.getQuantity());
            findCart.setTotalPrice(findCart.getTotalPrice().add(cart.getTotalPrice()));
        }
        else
            carts.add(cart);
    }

    private Cart findByFlowerName(String flowerName){
        for(Cart c:carts) {
            if (c.getFlowerName().equals(flowerName))
                return c;
        }
        return null;
    }

    public void clearCart(){
        carts.clear();
    }

    public void addOrder(Order order){
        orders.add(order);
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

    public BigDecimal getBalance(){
        return balance;
    }

    public int getDiscount() {
        return discount;
    }

    public void setBalance(BigDecimal balance){
        this.balance=balance;
    }

    public BigDecimal getCartCost() {
        return cartCost;
    }

    public void setCartCost(BigDecimal cartCost) {
        this.cartCost = cartCost;
    }

    public void setCartCostWithDiscount(BigDecimal totalPrice) {
        cartCost = (cartCost.add(totalPrice
                .multiply(new BigDecimal((100.00-discount)/100.00))))
                .setScale(2, RoundingMode.CEILING);
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
