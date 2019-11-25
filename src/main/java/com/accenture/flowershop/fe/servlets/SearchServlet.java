package com.accenture.flowershop.fe.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/searchServlet")
public class SearchServlet extends HttpServlet {
    private String message;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            request.getRequestDispatcher("/loginFormServlet").forward(request, response);
        } else {
            message = null;
            if (request.getParameter("search") != null) {
                searchFlowers(session, request);
            }

            if (request.getParameter("cancel") != null) {
                cancelSearch(session);
            }

            session.setAttribute("message", message);
            response.sendRedirect("userFormServlet");
        }
    }

    private void searchFlowers(HttpSession session, HttpServletRequest request) {
        String nameToSearch = request.getParameter("nameToSearch");
        String priceFrom = request.getParameter("priceFrom");
        String priceTo = request.getParameter("priceTo");
        if (!nameToSearch.isEmpty() || !priceFrom.isEmpty() || !priceTo.isEmpty()) {
            session.setAttribute("foundedFlowers", "find");
            session.setAttribute("nameToSearch", nameToSearch);
            session.setAttribute("priceFrom", priceFrom);
            session.setAttribute("priceTo", priceTo);
        } else
            message = "Enter any values to search";
    }

    private void cancelSearch(HttpSession session) {
        if (session.getAttribute("foundedFlowers") != null) {
            session.setAttribute("foundedFlowers", null);
        }
    }
}
