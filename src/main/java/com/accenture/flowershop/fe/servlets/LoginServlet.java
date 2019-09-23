package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.mapper.Mapper;
import com.accenture.flowershop.fe.dto.*;
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
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User user=userBusinessService.userLogin(username, password);
        if(user!=null) {
            UserDTO userDTO=mapper.map(user, UserDTO.class);
            HttpSession session=request.getSession();
            session.setMaxInactiveInterval(20*60);
            if(user.isAdmin()) {
                userDTO.setAdmin(true);
                session.setAttribute("orders",
                        mapper.mapList(orderBusinessService.getOrders(), OrderDTO.class));
                request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
            }
            else {
                session.setAttribute("user",userDTO);
                session.setAttribute("flowers",
                        mapper.mapList(flowerBusinessService.getFlowers(), FlowerDTO.class));
                session.setAttribute("orders",
                        mapper.mapList(orderBusinessService.getOrders(user.getLogin()), OrderDTO.class));
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
            }
        }
        else {
            request.setAttribute("message","Invalid username or password entered!");
            request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
        }
    }
}
