package com.accenture.flowershop.be.business.jms;

public class DiscountRequestObject {
    private String login;
    private int discount;

    public DiscountRequestObject(){}
    public DiscountRequestObject(String login,int discount){
        this.login=login;
        this.discount=discount;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getLogin() {
        return login;
    }

    public int getDiscount() {
        return discount;
    }
}
