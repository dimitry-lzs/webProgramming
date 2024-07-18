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
import com.webProgramming.models.User;

@WebServlet("/register-admin")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static final UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String confirmPassword = request.getParameter("confirmPassword");

            if (username == null || password == null || confirmPassword == null) {
                throw new Exception("Username, password, or confirm password is null");
            }

            if (!password.equals(confirmPassword)) {
                throw new Exception("Passwords do not match");
            }

            Admin admin = new Admin(username, name, surname);
            byte[] salt = new byte[16];
            salt=admin.generateSalt();
            admin.setSalt(salt);
            admin.setPassword(User.hashPassword(password, salt));


            boolean registerSuccess = userDao.saveUser(admin);

            if (!registerSuccess) {
                throw new Exception("The user with this username already exists or an error has occurred");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/registerSuccess.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            dispatcher.forward(request, response);
        }
    }
}
