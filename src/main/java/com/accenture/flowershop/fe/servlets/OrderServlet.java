package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.order.OrderBusinessService;
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
            UserDTO user = (UserDTO)session.getAttribute("user");
            if (!orderBusinessService.createNewOrder(user)) {
                request.setAttribute("orderMessage", "Cart is empty!");
            }
            request.getRequestDispatcher("/userPage.jsp").include(request, response);
        }
    }
}
