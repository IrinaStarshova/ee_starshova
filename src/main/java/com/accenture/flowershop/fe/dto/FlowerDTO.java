package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

public class FlowerDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private int quantityInCart;

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

    public int getQuantityInCart() {
        return quantityInCart;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setQuantityInCart(int quantityInCart) {
        this.quantityInCart = quantityInCart;
    }

}
