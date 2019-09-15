package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.cart.CartBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/orderServlet")
public class OrderServlet extends HttpServlet {

    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private UserBusinessService userBusinessService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if(session==null)
            request.getRequestDispatcher("/loginFormServlet").forward(request, response);
        else {
            String login = ((UserDTO)session.getAttribute("user")).getLogin();
            if (!orderBusinessService.createNewOrder(login)) {
                request.setAttribute("orderMessage", "Cart is empty!");
            }
            else {

                session.setAttribute("user", userBusinessService.getUser(login));
                session.setAttribute("cart", null);
                session.setAttribute("orders", orderBusinessService.getOrders(login));
            }
            request.getRequestDispatcher("/userPage.jsp").forward(request, response);
        }
    }
}
