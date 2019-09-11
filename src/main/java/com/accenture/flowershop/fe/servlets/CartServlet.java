package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.cart.CartBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/cartServlet")
public class CartServlet extends HttpServlet {

    @Autowired
    private CartBusinessService cartBusinessService;

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
            else{
                Long id=Long.parseLong(request.getParameter("flowerID"));
                int quantity=Integer.parseInt(request.getParameter("quantity"));
                String login = ((UserDTO) session.getAttribute("user")).getLogin();
                BigDecimal cartCost =cartBusinessService.addToCart(id,quantity,login);
                if(cartCost!=null)
                    request.setAttribute("cartCost", cartCost);
                else
                    request.setAttribute("cartMessage", "Error adding to cart. Pay attention to the available number of flowers!");
                request.getRequestDispatcher("/userPage.jsp").include(request, response);
            }
    }
}
