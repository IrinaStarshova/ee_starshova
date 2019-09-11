package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.cart.CartBusinessService;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {

    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private CartBusinessService cartBusinessService;
    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        UserDTO user=userBusinessService.userLogin(username, password);
        if(user!=null) {
            HttpSession session=request.getSession();
            session.setMaxInactiveInterval(20*60);
            session.setAttribute("orders",orderBusinessService);
            session.setAttribute("cart", cartBusinessService);
            if(user.isAdmin())
                request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
            else {

                session.setAttribute("user",user);
                session.setAttribute("flowers",flowerBusinessService);
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
            }
        }
        else {
            request.setAttribute("message","Invalid username or password entered!");
            request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
        }
    }
}
