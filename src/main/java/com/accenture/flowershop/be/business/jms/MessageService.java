package com.accenture.flowershop.be.business.jms;

import java.io.IOException;

public interface MessageService {

    void sendCustomerXML(String filename) throws IOException;
}