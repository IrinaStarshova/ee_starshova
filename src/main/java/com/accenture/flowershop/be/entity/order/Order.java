package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.cart.Cart;
import com.accenture.flowershop.fe.enums.order.OrderStatuses;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "Order")
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "orders_seq", allocationSize = 1)
    @Column(name="id")
    private Long id;

    @Column(name="cost")
    private BigDecimal cost;

    @Column(name="creationDate")
    private Date creationDate;

    @Column(name="closingDate")
    private Date closingDate;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private OrderStatuses status;

    @Column(name="login")
    private String login;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    private List<Cart> carts=new ArrayList<>();

    public Order(){}
    public Order(Date creationDate, OrderStatuses status) {
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

    public OrderStatuses getStatus() {
        return status;
    }

    public void setStatus(OrderStatuses status){ this.status=status;}

    @Override
    public String toString() {
        return "||ID: " + id +
                " Cost: " + cost +
                " CreationDate: " + creationDate +
                " Status: " + status +"||";
    }
}
