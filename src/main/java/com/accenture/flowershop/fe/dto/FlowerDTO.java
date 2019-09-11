package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

public class FlowerDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private int quantityInCart;

    public FlowerDTO(Long id, String name, BigDecimal price, int quantity, int quantityInCart) {
        this.id=id;
        this.name =name;
        this.price=price;
        this.quantity=quantity;
        this.quantityInCart=quantityInCart;
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

}
