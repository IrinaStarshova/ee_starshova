package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.access.user.UserAccessService;
import com.accenture.flowershop.be.business.exceptions.UserExistException;
import com.accenture.flowershop.be.business.user.UserBusinessService;
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
    @Autowired
    private UserAccessService userAccessService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            String name = request.getParameter("username");
            userBusinessService.createNewUser(request.getParameter("username"),
                    request.getParameter("password"),
                    request.getParameter("firstName"),
                    request.getParameter("patronymic"),
                    request.getParameter("lastName"),
                    request.getParameter("address"),
                    request.getParameter("phoneNumber"));
            request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
        } catch (UserExistException e) {
            request.setAttribute("registrationMessage",
                    e.getMessage());
            request.getRequestDispatcher("/registerForm.jsp").forward(request, response);
        }
    }
}


