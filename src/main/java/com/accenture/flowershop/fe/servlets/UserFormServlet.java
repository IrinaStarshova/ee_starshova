package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
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

@WebServlet(urlPatterns = "/userFormServlet")
public class UserFormServlet extends HttpServlet {
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

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            request.getRequestDispatcher("/loginFormServlet").forward(request, response);
        } else {
            String login = ((UserDTO) session.getAttribute("user")).getLogin();
            session.setAttribute("user",
                    mapper.map(userBusinessService.getCustomer(login), UserDTO.class));
            if (session.getAttribute("foundedFlowers") == null) {
                session.setAttribute("flowers",
                        mapper.mapList(flowerBusinessService.getFlowers(), FlowerDTO.class));
            } else {
                session.setAttribute("flowers",
                        mapper.mapList
                                (flowerBusinessService.findFlowers
                                                ((String) session.getAttribute("nameToSearch"),
                                                        (String) session.getAttribute("priceFrom"),
                                                        (String) session.getAttribute("priceTo")),
                                        FlowerDTO.class));
            }
            Object message = session.getAttribute("message");
            if (message != null) {
                request.setAttribute("message", message);
                session.setAttribute("message", null);
            }
            request.getRequestDispatcher("/userPage.jsp").forward(request, response);
        }
    }
}
