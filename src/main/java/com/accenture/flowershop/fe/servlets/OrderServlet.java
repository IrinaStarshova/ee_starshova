package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.exceptions.UnavailableQuantityException;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.persistence.OptimisticLockException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/orderServlet")
public class OrderServlet extends HttpServlet {

    @Autowired
    private OrderBusinessService orderBusinessService;
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

            if (request.getParameter("createOrder") != null) {
                createOrder((UserDTO) session.getAttribute("user"));
            }

            if (request.getParameter("deleteOrder") != null) {
                deleteOrder(Long.parseLong(request.getParameter("orderId")));
            }

            if (request.getParameter("payOrder") != null) {
                payOrder(Long.parseLong(request.getParameter("orderId")));
            }
            session.setAttribute("message", message);
            response.sendRedirect("userFormServlet");
        }
    }

    private void createOrder(UserDTO userDTO) {
        if (userDTO.getCarts().isEmpty()) {
            message = "Cart is empty!";
        } else {
            try {
                orderBusinessService.createNewOrder(userDTO.getLogin());
            } catch (UnavailableQuantityException e) {
                message = e.getMessage();
            } catch (OptimisticLockException e) {
                message = "Error creating order. Try later";
            }
        }
    }

    private void deleteOrder(Long orderId) {
        try {
            orderBusinessService.deleteOrder(orderId);
        } catch (OptimisticLockException e) {
            message = "Error deleting order. Try later";
        }
    }

    private void payOrder(Long orderId) {
        if (!orderBusinessService.payOrder(orderId)) {
            message = "Order payment error. Pay attention to the current balance!";
        }
    }
}
