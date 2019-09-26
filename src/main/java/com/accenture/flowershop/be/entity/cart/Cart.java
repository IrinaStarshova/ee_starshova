package com.accenture.flowershop.be.entity.cart;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "Cart")
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "cart_seq", allocationSize = 1)
    @Column(name="id")
    private Long id;

    @Column(name="flower_id")
    private Long flowerId;

    @Column(name="flowerName")
    private String flowerName;

    @Column(name="quantity")
    private int quantity;

    @Column(name="total_price")
    private BigDecimal totalPrice;

    @Column(name="login")
    private String login;

    @Column(name="order_id")
    private Long orderId;

    public Cart(){}
    public Cart(Long flowerId, String flowerName, int quantity, BigDecimal totalPrice) {
        this.flowerId=flowerId;
        this.flowerName =flowerName;
        this.quantity=quantity;
        this.totalPrice=totalPrice;
    }

    public Long getId() {
        return id;
    }

    public Long getFlowerId() {
        return flowerId;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "||Name of flower: " + flowerName +
                " Quantity: " + quantity+
                " Price: " + totalPrice+"||";
    }

}
