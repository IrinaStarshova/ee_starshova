package com.accenture.flowershop.fe.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/loginFormServlet")
public class LoginFormServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
    }
}
