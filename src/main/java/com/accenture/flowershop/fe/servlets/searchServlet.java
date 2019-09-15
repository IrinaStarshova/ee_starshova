package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
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

@WebServlet(urlPatterns = "/searchServlet")
public class searchServlet extends HttpServlet {

    @Autowired
    private FlowerBusinessService flowerBusinessService;

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
                if(request.getParameter("cancel")==null) {
                    String nameToSearch = request.getParameter("nameToSearch");
                    String priceFrom=request.getParameter("priceFrom");
                    String priceTo=request.getParameter("priceTo");
                    if (!nameToSearch.isEmpty() || !priceFrom.isEmpty() || !priceTo.isEmpty()) {
                        session.setAttribute("foundedFlowers", "find");
                        session.setAttribute("flowers",
                                flowerBusinessService.findFlowers(nameToSearch,priceFrom,priceTo));
                        session.setAttribute("nameToSearch", nameToSearch);
                        session.setAttribute("priceFrom", priceFrom);
                        session.setAttribute("priceTo", priceTo);
                    }
                    else
                        request.setAttribute("searchMessage", "Enter any values to search");
                }
                else {
                    if(session.getAttribute("foundedFlowers")!=null){
                        session.setAttribute("foundedFlowers", null);
                        session.setAttribute("flowers", flowerBusinessService.getFlowers());
                    }
                }
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
            }
    }
}
