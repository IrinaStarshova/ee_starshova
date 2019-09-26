package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.fe.enums.order.OrderStatus;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "Order")
@Table(name = "order_")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "order_seq", allocationSize = 1)
    @Column(name="id")
    private Long id;

    @Column(name="cost")
    private BigDecimal cost;

    @Column(name="creation_date")
    private Date creationDate;

    @Column(name="closing_date")
    private Date closingDate;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name="login")
    private String login;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<Cart> carts=new ArrayList<>();

    public Order(){}
    public Order(Date creationDate, OrderStatus status) {
        this.creationDate=creationDate;
        this.status=status;
    }

    public void addCart(Cart cart){
        carts.add(cart);
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
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

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status){ this.status=status;}

    @Override
    public String toString() {
        return "||ID: " + id +
                " Cost: " + cost +
                " CreationDate: " + creationDate +
                " Status: " + status +"||";
    }
}
