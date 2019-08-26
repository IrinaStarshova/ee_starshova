package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.be.entity.user.UserImpl;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class DBEmulation implements UserAccessService{
    private  static Map<String, User> usersDB=new HashMap<>();
    static {
        usersDB.put("admin",new UserImpl("admin", "admin123"));
        usersDB.put("John",new UserImpl("John", "123"));
    }


    public boolean login(User user){
        return usersDB.containsValue(user);
    }
    public boolean  addUser(User user){

        if(usersDB.containsKey(user.getName()))
            return false;
        usersDB.put(user.getName(),user);
        System.out.println(usersDB);
        return true;
    }
}
