package com.accenture.flowershop.be.entity.user;

public class UserImpl implements User {
    private String name;
    private String password;

    public UserImpl(String name, String password) {
        this.name=name;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Username: " + name +
                " Password: " + password;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
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
        if (!name.equals(userObj.getName()))
            return false;
        if (!password.equals(userObj.getPassword()))
            return false;
        return true;
    }
}
