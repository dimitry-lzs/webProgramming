package com.webProgramming.controllers;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webProgramming.daos.UserDao;
import com.webProgramming.models.enums.UserType;

@WebServlet("/admin")
public class AdminController extends HttpServlet {

    // @Override
    // protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {

    // }

    static final UserDao userDao = new UserDao();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        // req.setAttribute("type", "ADMIN");
        // String controler = req.getParameter("controler");
        // RequestDispatcher dispatcher = null;

        // if ("SELLER".equals(controler)){
        //     String username = req.getParameter("username");
        //     String password = req.getParameter("password");

        //     int ID = userDao.getId(username, password, UserType.ADMIN);

        //     req.setAttribute("username", username);
        //     req.setAttribute("password", password);
        //     req.setAttribute("ID", ID);
        //     dispatcher = req.getRequestDispatcher("admin/AddSeller.jsp");
        // }
        // dispatcher.forward(req, resp);
    }
}

