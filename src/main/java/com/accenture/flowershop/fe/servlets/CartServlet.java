package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.cart.CartBusinessService;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/cartServlet")
public class CartServlet extends HttpServlet {

    @Autowired
    private CartBusinessService cartBusinessService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
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
            else{
                Long id=Long.parseLong(request.getParameter("flowerID"));
                int quantity=Integer.parseInt(request.getParameter("quantity"));
                int availableQuantity=Integer.parseInt(request.getParameter("availableQuantity"));
                String login = ((UserDTO) session.getAttribute("user")).getLogin();
                if(cartBusinessService.addToCart(id,availableQuantity,quantity,login)) {
                    if(session.getAttribute("foundedFlowers")==null)
                        session.setAttribute("flowers", flowerBusinessService.getFlowers());
                    else
                        session.setAttribute("flowers",
                                flowerBusinessService.findFlowers
                                        ((String)session.getAttribute("nameToSearch"),
                                                (String)session.getAttribute("priceFrom"),
                                                (String)session.getAttribute("priceTo")));
                    session.setAttribute("user", userBusinessService.getUser(login));
                    session.setAttribute("cart", cartBusinessService.getCart(login));
                }
                else {
                    request.setAttribute("cartMessage",
                            "Error adding to cart. Pay attention to the available number of flowers!");
                }
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
            }
    }
}
