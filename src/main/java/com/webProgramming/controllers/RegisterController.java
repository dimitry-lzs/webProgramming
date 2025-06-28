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
import com.webProgramming.models.User;
import com.webProgramming.services.UserService;
import com.webProgramming.src.Generic;

@WebServlet("/register-admin")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService userService;

    private final String NAME_PARAM = "name";
    private final String SURNAME_PARAM = "surname";
    private final String USERNAME_PARAM = "username";
    private final String PASSWORD_PARAM = "password";
    private final String CONFIRM_PASSWORD_PARAM = "confirmPassword";

    public RegisterController() {
        this.userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            setCharacterEncoding(request, response);
            Admin admin = createAdminFromRequest(request);

            userService.saveUser(admin);

            ServletUtils.forwardToPage(request, response, Generic.REGISTER_SUCCESS);
        } catch (Exception exception) {
            exception.printStackTrace();
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("errorMessage", exception.getMessage());
            ServletUtils.forwardToPage(request, response, Generic.ERROR_PAGE, attributes);
        }
    }

    private void setCharacterEncoding(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
        } catch (Exception exception) {
            throw new IOException("Error setting character encoding");
        }
    }
 
    private Admin createAdminFromRequest(HttpServletRequest request) throws NoSuchAlgorithmException {
        String name = request.getParameter(NAME_PARAM);
        String surname = request.getParameter(SURNAME_PARAM);
        String username = request.getParameter(USERNAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD_PARAM);

        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        Admin admin = new Admin(username, name, surname);
        admin.setPassword(password);

        byte[] salt = new byte[16];
        salt = admin.generateSalt();
        admin.setSalt(salt);
        admin.setPassword(User.hashPassword(password, salt));

        return admin;
    }
}
