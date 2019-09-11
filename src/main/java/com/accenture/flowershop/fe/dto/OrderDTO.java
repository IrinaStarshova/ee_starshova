package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class OrderDTO {

    private Long id;
    private BigDecimal cost;
    private Date creationDate;
    private Date closingDate;
    private String status;

    public OrderDTO(Long id, BigDecimal cost, Date creationDate, Date closingDate, String status) {
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

    public String getStatus() {
        return status;
    }

}
