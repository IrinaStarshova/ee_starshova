package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.user.User;
import java.io.IOException;

public interface UserMarshallingService {
    void convertFromUserToXML(User user, String filename) throws IOException;
    Object convertXMLStringToObject(String string) throws IOException;
    String convertObjectToXMLString(Object obj) throws IOException;
}
