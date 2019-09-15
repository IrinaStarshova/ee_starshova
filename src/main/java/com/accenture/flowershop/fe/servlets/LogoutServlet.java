package com.accenture.flowershop.fe.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/logoutServlet")
public class LogoutServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        if(request.getSession(false)!=null)
            request.getSession().invalidate();
        request.getRequestDispatcher("/loginFormServlet").forward(request, response);
    }
}
