package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.be.entity.user.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Autowired
    private UserBusinessService userBusinessService;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        User user=new UserImpl(request.getParameter("username"),
                request.getParameter("password"));
        if(userBusinessService.createNewUser(user)){
            request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
        }
        else {
            request.setAttribute("message","User with the same name already exists!");
            request.getRequestDispatcher("/registerForm.jsp").forward(request, response);
        }
    }
}
