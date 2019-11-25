package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.cart.CartBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/cartServlet")
public class CartServlet extends HttpServlet {

    @Autowired
    private CartBusinessService cartBusinessService;
    private String message;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            request.getRequestDispatcher("/loginFormServlet").forward(request, response);
        } else {
            message = null;
            String login = ((UserDTO) session.getAttribute("user")).getLogin();

            if (request.getParameter("addToCart") != null) {
                addCartItem(Long.parseLong(request.getParameter("flowerID")),
                        Integer.parseInt(request.getParameter("quantity")),
                        login);
            }

            if (request.getParameter("clearCart") != null) {
                cartBusinessService.clearCart(login);
            }
            session.setAttribute("message", message);
            response.sendRedirect("userFormServlet");
        }
    }

    private void addCartItem(Long flowerID, int quantity, String login) {
        if (!(cartBusinessService.addToCart(flowerID, quantity, login))) {
            message = "Error adding to cart. Pay attention to the available number of flowers!";
        }
    }
}
