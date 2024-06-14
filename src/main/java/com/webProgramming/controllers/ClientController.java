package com.webProgramming.controllers;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webProgramming.models.Client;
import com.webProgramming.models.Seller;
import com.webProgramming.models.UserDao;
import com.webProgramming.src.Login;
import com.webProgramming.models.enums.UserType;

@WebServlet("/client")
public class ClientController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        try {
            Login loggedInSeller = (Login) request.getSession().getAttribute("user");

            if (loggedInSeller == null) {
                response.sendRedirect("/login.jsp");
                return;
            }

            if (loggedInSeller.getType() != UserType.SELLER) {
                throw new IllegalArgumentException("Only sellers can create clients");
            }

            Seller seller = (Seller) loggedInSeller.getUser();

            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String afm = request.getParameter("afm");

            if (name == null || surname == null || username == null || password == null || afm == null || seller == null || confirmPassword == null) {
                throw new IllegalArgumentException("Missing parameters");
            }

            if (!password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Passwords do not match");
            }

            Client client = new Client();
            client.setUsername(username);
            client.setPassword(password);
            client.setName(name);
            client.setSurname(surname);
            client.setAfm(password);
            client.setSeller(seller);

            UserDao userDao = new UserDao();
            userDao.saveUser(client);

            RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
            request.setAttribute("message", "Client created successfully");
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
