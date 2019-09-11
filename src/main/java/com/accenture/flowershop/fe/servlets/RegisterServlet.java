package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
        if(userBusinessService.createNewUser(request.getParameter("username"),
                                            request.getParameter("password"),
                                            request.getParameter("firstName"),
                                            request.getParameter("patronymic"),
                                            request.getParameter("lastName"),
                                            request.getParameter("address"),
                                            request.getParameter("phoneNumber"))){
            request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
        }
        else {
            request.setAttribute("message","User with the same username already exists!");
            request.getRequestDispatcher("/registerForm.jsp").forward(request, response);
        }
    }
}
