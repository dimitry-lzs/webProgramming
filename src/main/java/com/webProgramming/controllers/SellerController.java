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
import com.webProgramming.models.User;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.src.Login;

@WebServlet("/seller")
public class SellerController extends HttpServlet {
    static final UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectLink = request.getContextPath() + "/admin/menu.jsp";

        try {
            Login loggedInUser = (Login) request.getSession().getAttribute("user");

            if (loggedInUser == null) {
                redirectLink = request.getContextPath() + "/index.jsp";
                throw new SecurityException("You are not logged in.");
            }

            if (loggedInUser.getType() != UserType.ADMIN) {
                redirectLink = request.getContextPath() + User.getRedirectionLink(loggedInUser.getType().name());
                throw new SecurityException("Permission denied.");
            }

            Admin admin = (Admin) loggedInUser.getUser();

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

            Seller seller = admin.createSeller(username, name, surname, password);
            UserDao userDao = new UserDao();
            boolean created = userDao.saveUser(seller);

            if (!created) {
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
