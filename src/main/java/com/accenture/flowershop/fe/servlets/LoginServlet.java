package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.mapper.Mapper;
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userBusinessService.userLogin(username, password);
        if (user != null) {
            if (user.isAdmin()) {
                response.sendRedirect("adminPage.html");
            } else {
                UserDTO userDTO = mapper.map(user, UserDTO.class);
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(20 * 60);
                session.setAttribute("user", userDTO);
                session.setAttribute("flowers",
                        mapper.mapList(flowerBusinessService.getFlowers(), FlowerDTO.class));
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "Invalid username or password entered!");
            request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
        }
    }
}
