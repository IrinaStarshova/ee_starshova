package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.cart.CartBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionDestroyedListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        UserDTO userDTO=(UserDTO) httpSessionEvent.getSession().getAttribute("user");
        if(!userDTO.isAdmin()) {
            String login = userDTO.getLogin();
            CartBusinessService cartBusinessService = (CartBusinessService) httpSessionEvent
                    .getSession().getAttribute("cart");
            if (cartBusinessService != null)
                cartBusinessService.clearCart(login);
        }
    }
}
