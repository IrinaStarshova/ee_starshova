package com.accenture.flowershop.be.entity.user;

import javax.persistence.*;

@Entity(name = "User")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "users")
public class User {
    @Id
    @Column(name="login")
    private String login;
    @Column(name="password")
    private String password;

    public User(){}

    public User(String name, String password) {
        this.login =name;
        this.password=password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return getClass() == User.class;
    }

    @Override
    public String toString() {
        return "Username: " + login;
    }
}
