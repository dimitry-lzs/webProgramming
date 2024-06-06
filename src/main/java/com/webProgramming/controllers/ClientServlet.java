package com.webProgramming.controllers;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/client")
public class ClientServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        req.setAttribute("type", "CLIENT");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/loginPage.jsp");
        dispatcher.forward(req, resp);
    }
}
