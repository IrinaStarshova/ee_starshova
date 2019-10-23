package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.exceptions.UnavailableQuantityException;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

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
    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private Mapper mapper;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null)
            request.getRequestDispatcher("/loginFormServlet").forward(request, response);
        else {
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            String login = userDTO.getLogin();

            if (request.getParameter("createOrder") != null) {
                createOrder(request, userDTO, login);
            }

            if (request.getParameter("deleteOrder") != null) {
                deleteOrder(request, login);
            }

            if (request.getParameter("payOrder") != null) {
                payOrder(request, login);
            }

            session.setAttribute("user",
                    mapper.map(userBusinessService.getCustomer(login), UserDTO.class));
            if (session.getAttribute("foundedFlowers") == null)
                session.setAttribute("flowers",
                        mapper.mapList(flowerBusinessService.getFlowers(), FlowerDTO.class));
            else
                session.setAttribute("flowers",
                        mapper.mapList
                                (flowerBusinessService.findFlowers
                                                ((String) session.getAttribute("nameToSearch"),
                                                        (String) session.getAttribute("priceFrom"),
                                                        (String) session.getAttribute("priceTo")),
                                        FlowerDTO.class));

            request.getRequestDispatcher("/userPage.jsp").forward(request, response);

        }
    }

    private void createOrder(HttpServletRequest request,
                             UserDTO userDTO, String login) {
        if (userDTO.getCarts().isEmpty())
            request.setAttribute("orderMessage", "Cart is empty!");
        else {
            try {
                orderBusinessService.createNewOrder(login);
            } catch (UnavailableQuantityException e) {
                request.setAttribute("orderMessage", e.getMessage());
            } catch (ObjectOptimisticLockingFailureException e) {
                request.setAttribute("orderMessage",
                        "Error creating order. Try later");
            }
        }
    }

    private void deleteOrder(HttpServletRequest request, String login) {
        try {
            orderBusinessService.deleteOrder
                    (Long.parseLong(request.getParameter("orderId")), login);
        } catch (ObjectOptimisticLockingFailureException e) {
            request.setAttribute("orderMessage",
                    "Error deleting order. Try later");
        }
    }

    private void payOrder(HttpServletRequest request, String login) {
        if (!userBusinessService.payOrder(login,
                Long.parseLong(request.getParameter("orderId")))) {

            request.setAttribute("payMessage",
                    "Order payment error. Pay attention to the current balance!");
        }
    }
}
