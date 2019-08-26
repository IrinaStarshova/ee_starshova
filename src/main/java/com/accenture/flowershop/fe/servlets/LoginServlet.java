package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.be.entity.user.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
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
        if(userBusinessService.userLogin(user)) {
            //HttpSession session = request.getSession();
            //session.setAttribute("username", request.getParameter("username"));
           // session.setMaxInactiveInterval(30*60);
            request.getRequestDispatcher("/userPage.jsp").forward(request, response);
        }
        else {
            request.setAttribute("message","Invalid username or password entered!");
            request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
        }
    }
}
