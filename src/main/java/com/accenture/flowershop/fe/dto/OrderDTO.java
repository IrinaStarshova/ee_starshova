package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.fe.enums.order.OrderStatuses;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private Long id;
    private BigDecimal cost;
    private Date creationDate;
    private Date closingDate;
    private OrderStatuses status;
    private List<CartDTO> carts=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public OrderStatuses getStatus() {
        return status;
    }

    public List<CartDTO> getCarts() {
        return carts;
    }

    public void addCart(CartDTO cart){
        carts.add(cart);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public void setStatus(OrderStatuses status) {
        this.status = status;
    }

    public void setCarts(List<CartDTO> carts) {
        this.carts = carts;
    }
}
