package com.accenture.flowershop.be.entity.cart;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.user.User;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "Cart")
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "cart_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flower_id")
    private Flower flower;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login")
    private User customer;

    public Cart() {
    }

    public Cart(Flower flower, int quantity, BigDecimal totalPrice) {
        this.flower = flower;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public void addFlowers(int quantity, BigDecimal totalPrice) {
        this.quantity += quantity;
        this.totalPrice = this.totalPrice.add(totalPrice);
    }

    public Long getId() {
        return id;
    }

    public Flower getFlower() {
        return flower;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public User getCustomer() {
        return customer;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "||Name of flower: " + flower.getName() +
                " Quantity: " + quantity +
                " Price: " + totalPrice +
                " User: " + customer.getLogin() +
                "||";
    }
}
