package com.webProgramming.controllers;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webProgramming.models.UserDao;
import com.webProgramming.models.enums.UserType;

@WebServlet("/seller")
public class SellerServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        req.setAttribute("type", "SELLER");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/loginPage.jsp");
        dispatcher.forward(req, resp);
    }


    static final UserDao userDao = new UserDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        req.setAttribute("type", "SELLER");
        String controler = req.getParameter("controler");
        RequestDispatcher dispatcher = null;

        if ("CLIENT".equals(controler)){
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            int ID = userDao.getId(username, password, UserType.SELLER);

            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("ID", ID);
            dispatcher = req.getRequestDispatcher("seller/AddClient.jsp");
        }
        dispatcher.forward(req, resp);
        
    }




}