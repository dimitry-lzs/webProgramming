package com.webProgramming.controllers;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webProgramming.models.Admin;
import com.webProgramming.models.Seller;
import com.webProgramming.models.UserDao;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.src.Login;

@WebServlet("/seller")
public class SellerController extends HttpServlet {
    static final UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Login loggedInSeller = (Login) request.getSession().getAttribute("user");

            if (loggedInSeller == null) {
                response.sendRedirect("/login.jsp");
                return;
            }

            if (loggedInSeller.getType() != UserType.ADMIN) {
                throw new IllegalArgumentException("Only admin can create sellers");
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

            Seller seller = new Seller();
            seller.setUsername(username);
            seller.setPassword(password);
            seller.setName(name);
            seller.setSurname(surname);
            seller.setAdmin(admin);

            UserDao userDao = new UserDao();
            userDao.saveUser(seller);

            RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
            request.setAttribute("message", "Seller created successfully");
            request.setAttribute("title", "Success");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            dispatcher.forward(request, response);;
        }
    }
}
