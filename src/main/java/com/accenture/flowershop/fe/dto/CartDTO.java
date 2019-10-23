package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

public class CartDTO {

    private Long id;
    private Long flowerId;
    private String flowerName;
    private int quantity;
    private BigDecimal totalPrice;

    public Long getId() {
        return id;
    }

    public Long getFlowerId() {
        return flowerId;
    }

    public String getFlowerName() {
        return flowerName;
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

    public void setFlowerId(Long flowerId) {
        this.flowerId = flowerId;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
