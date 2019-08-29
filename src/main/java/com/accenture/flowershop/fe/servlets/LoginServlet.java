package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {

    private static final String ADMIN="admin";
    @Autowired
    private UserBusinessService userBusinessService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        if(userBusinessService.userLogin(username, password)) {
            if(username.equals(ADMIN))
                request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
            else
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
        }
        else {
            request.setAttribute("message","Invalid username or password entered!");
            request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
        }
    }
}
