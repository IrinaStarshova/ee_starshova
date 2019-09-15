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

    public OrderDTO(Long id, BigDecimal cost, Date creationDate,
                    Date closingDate, OrderStatuses status) {
        this.id=id;
        this.cost=cost;
        this.creationDate=creationDate;
        this.closingDate=closingDate;
        this.status=status;
    }

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
}
