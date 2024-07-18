package com.webProgramming.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webProgramming.models.Admin;
import com.webProgramming.models.Seller;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.services.UserService;
import com.webProgramming.src.AdminPages;
import com.webProgramming.src.Generic;
import com.webProgramming.src.Login;

@WebServlet("/seller")
public class SellerController extends HttpServlet {
    static final long serialVersionUID = 1L;
    private final UserService userService;

    private final String USERNAME_PARAM = "username";
    private final String PASSWORD_PARAM = "password";
    private final String NAME_PARAM = "name";
    private final String SURNAME_PARAM = "surname";
    private final String CONFIRM_PASSWORD_PARAM = "confirmPassword";

    public SellerController() {
        this.userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Admin admin = (Admin) verifyAdmin(request).getUser();
            Seller seller = creatSeller(request, admin);

            userService.saveUser(seller);

            ServletUtils.forwardToPage(request, response, Generic.SUCCESS, Map.of(
                    "message", "Seller created successfully",
                    "link", request.getContextPath() + AdminPages.ADMIN_MENU,
                    "title", "Success"));

        } catch (Exception exception) {
            handleException(request, response, exception);
        }
    }

    private Seller creatSeller(HttpServletRequest request, Admin admin) throws NoSuchAlgorithmException {
        String name = request.getParameter(NAME_PARAM);
        String surname = request.getParameter(SURNAME_PARAM);
        String username = request.getParameter(USERNAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD_PARAM);

        if (name == null || surname == null || username == null || password == null || confirmPassword == null) {
            throw new IllegalArgumentException("Missing parameters");
        }

        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        return admin.createSeller(username, name, surname, password);
    }

    private Login verifyAdmin(HttpServletRequest request) {
        Login loggedInUser = (Login) request.getSession().getAttribute("user");

        if (loggedInUser == null) {
            throw new SecurityException("You are not logged in.");
        }

        if (loggedInUser.getType() != UserType.ADMIN) {
            throw new SecurityException("Permission denied.");
        }

        return loggedInUser;
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, Exception exception)
            throws ServletException, IOException {
        exception.printStackTrace();
        Login loggedInUser = (Login) request.getSession().getAttribute("user");
        String link = request.getContextPath() + Generic.INDEX_PAGE;

        if (loggedInUser != null) {
            link = request.getContextPath() + loggedInUser.getType().getRedirectPath();
        }
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("errorMessage", exception.getMessage());
        attributes.put("link", link);
        ServletUtils.forwardToPage(request, response, Generic.ERROR_PAGE, attributes);
    }
}
