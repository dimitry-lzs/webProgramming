package com.webProgramming;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        req.setAttribute("user", "client");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/loginPage.jsp");
        dispatcher.forward(req, resp);
    }
}