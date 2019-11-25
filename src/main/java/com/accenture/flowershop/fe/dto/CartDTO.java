package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

public class CartDTO {

    private Long id;
    private FlowerDTO flower;
    private int quantity;
    private BigDecimal totalPrice;

    public Long getId() {
        return id;
    }

    public FlowerDTO getFlower() {
        return flower;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFlower(FlowerDTO flower) {
        this.flower = flower;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
