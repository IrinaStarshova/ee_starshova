package com.accenture.flowershop.fe.dto;

import javax.persistence.*;
import java.math.BigDecimal;

public class CartDTO {

    private Long id;
    private String flowerName;
    private int quantity;
    private BigDecimal totalPrice;

    public CartDTO(Long id, String flowerName, int quantity, BigDecimal totalPrice) {
        this.id=id;
        this.flowerName =flowerName;
        this.quantity=quantity;
        this.totalPrice=totalPrice;
    }

    public Long getId() {
        return id;
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

}
