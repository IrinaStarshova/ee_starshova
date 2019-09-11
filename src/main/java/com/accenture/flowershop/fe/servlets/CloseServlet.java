package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.order.OrderBusinessService;
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

@WebServlet(urlPatterns = "/closeServlet")
public class CloseServlet extends HttpServlet {

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
            orderBusinessService.closeOrder(Long.parseLong(request.getParameter("orderId")));
            request.getRequestDispatcher("/adminPage.jsp").include(request, response);
        }
    }
}
