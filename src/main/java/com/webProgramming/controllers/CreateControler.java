package com.webProgramming.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import com.webProgramming.models.Seller;
import com.webProgramming.daos.UserDao;
import com.webProgramming.models.Admin;
import com.webProgramming.models.Client;
import com.webProgramming.models.enums.UserType;

import java.io.IOException;


@WebServlet("/create")
public class CreateControler extends HttpServlet{
    static final UserDao userDao = new UserDao();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        try {
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");

            String newUsername = request.getParameter("newUsername");
            String newPassword = request.getParameter("newPassword");
            String newName = request.getParameter("newName");
            String newSurname = request.getParameter("newSurname");
            String newConfirmPassword = request.getParameter("newConfirmPassword");

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            int ID = Integer.parseInt(request.getParameter("ID"));
            String type = request.getParameter("type");
            String option = request.getParameter("option");


            if (newUsername == null || newPassword == null || newConfirmPassword == null) {
                throw new Exception("Username, password, or confirm password is null");
            }

            if (!newPassword.equals(newConfirmPassword)) {
                throw new Exception("Passwords do not match");
            }

            boolean registerSuccess = false;

            UserType CreateuserType = UserType.valueOf(option);
            switch(CreateuserType) {
                case SELLER:

                    Admin adminCreator = (Admin) userDao.findById(ID, UserType.ADMIN);
                    Seller seller = adminCreator.createSeller(newUsername, newName, newSurname, newPassword);

                    registerSuccess = userDao.saveUser(seller);
                    break;
                case CLIENT:
                    String AFM = request.getParameter("AFM");
                    int phonenumber = Integer.parseInt(request.getParameter("phonenumber"));


                    Seller sellerCreator = (Seller) userDao.findById(ID, UserType.SELLER);
                    Client client = sellerCreator.createClient(AFM, phonenumber, newUsername, newName, newSurname, newPassword);

                    registerSuccess = userDao.saveUser(client);
                    break;
                case ADMIN:
                    break;
            }

            if (!registerSuccess) {
                throw new Exception("The user with this username already exists or an error has occurred");
            }

            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("type", type);
            request.setAttribute("option", option);


            RequestDispatcher dispatcher = request.getRequestDispatcher("/createUserSuccess.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            dispatcher.forward(request, response);
        }

    }
}
