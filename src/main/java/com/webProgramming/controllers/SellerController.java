package com.webProgramming.controllers;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webProgramming.daos.UserDao;
import com.webProgramming.models.Admin;
import com.webProgramming.models.Seller;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.src.Login;

@WebServlet("/seller")
public class SellerController extends HttpServlet {
    static final UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectLink = request.getContextPath() + "/admin/menu.jsp";

        try {
            Login loggedInSeller = (Login) request.getSession().getAttribute("user");

            if (loggedInSeller == null || loggedInSeller.getType() != UserType.ADMIN) {
                redirectLink = request.getContextPath() + "/login.jsp";
                throw new SecurityException("Permission denied.");
            }

            Admin admin = (Admin) loggedInSeller.getUser();

            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            if (name == null || surname == null || username == null || password == null || admin == null || confirmPassword == null) {
                throw new IllegalArgumentException("Missing parameters");
            }

            if (!password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Passwords do not match");
            }
            boolean userCreated = false;
            try{
                Seller seller = admin.createSeller(username, name, surname, password);
                UserDao userDao = new UserDao();
                userCreated = userDao.saveUser(seller);
            }catch(Exception e){
                redirectLink = request.getContextPath() + "/admin/AddSeller.jsp";
                throw new IllegalArgumentException("Username is already been used.\nTry another username.");
            }


            if (!userCreated) {
                throw new IllegalArgumentException("Seller could not be created");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
            request.setAttribute("link", request.getContextPath() + "/admin/menu.jsp");
            request.setAttribute("message", "Seller created successfully");
            request.setAttribute("title", "Success");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("link", redirectLink);
            dispatcher.forward(request, response);;
        }
    }
}
