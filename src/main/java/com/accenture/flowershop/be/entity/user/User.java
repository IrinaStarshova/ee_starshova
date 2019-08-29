package com.accenture.flowershop.be.entity.user;

import javax.persistence.*;

@Entity(name = "User")
@Table(name = "USERS")
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

    @Override
    public String toString() {
        return "Username: " + login +
                " Password: " + password;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + login.hashCode();
        result = prime * result + password.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User userObj = (User) obj;
        if (!login.equals(userObj.getLogin()))
            return false;
        if (!password.equals(userObj.getPassword()))
            return false;
        return true;
    }
}
