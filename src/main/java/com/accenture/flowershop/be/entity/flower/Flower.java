package com.accenture.flowershop.be.entity.flower;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "Flower")
@Table(name = "flowers")
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "flowers_seq", allocationSize = 1)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="quantity")
    private int quantity;

    @Column(name="quantityInCart")
    private int quantityInCart;

    public Flower(){}
    public Flower(String name, BigDecimal price, int quantity) {
        this.name =name;
        this.price=price;
        this.quantity=quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityInCart() {
        return quantityInCart;
    }

    public void setQuantityInCart(int quantityInCart) {
        this.quantityInCart = quantityInCart;
    }

    @Override
    public String toString() {
        return "||Name of flower: " + name +
                " Price: " + price +
                "Quantity: " + quantity +"||";
    }

}
