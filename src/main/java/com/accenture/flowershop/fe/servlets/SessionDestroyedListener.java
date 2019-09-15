package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.cart.CartBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionDestroyedListener implements ServletContextListener,HttpSessionListener {

    @Autowired
    private CartBusinessService cartBusinessService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext
                (this,servletContextEvent.getServletContext());
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        UserDTO userDTO=(UserDTO) httpSessionEvent.getSession().getAttribute("user");
        if(!userDTO.isAdmin()) {
            String login = userDTO.getLogin();
            cartBusinessService.clearCart(login);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
