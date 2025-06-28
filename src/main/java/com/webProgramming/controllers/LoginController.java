package com.webProgramming.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webProgramming.models.enums.UserType;
import com.webProgramming.services.UserService;
import com.webProgramming.src.Generic;
import com.webProgramming.src.Login;
import com.webProgramming.exceptions.AuthenticationException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    static final long serialVersionUID = 1L;
    private final UserService userService;

    private final String USERNAME_PARAM = "username";
    private final String PASSWORD_PARAM = "password";
    private final String TYPE_PARAM = "type";

    public LoginController() {
        this.userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            setCharacterEncoding(request, response);
            Login login = authenticateUser(request);

            HttpSession session = request.getSession();
            session.setAttribute("user", login);

            String redirectPath = login.getType().getRedirectPath();

            ServletUtils.forwardToPage(request, response, redirectPath);
        } catch (Exception exception) {
            exception.printStackTrace();
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("errorMessage", exception.getMessage());
            ServletUtils.forwardToPage(request, response, Generic.ERROR_PAGE, attributes);
        }
    }

    private Login authenticateUser(HttpServletRequest request) throws AuthenticationException {
        try {
            String username = request.getParameter(USERNAME_PARAM);
            String password = request.getParameter(PASSWORD_PARAM);

            String type = request.getParameter(TYPE_PARAM);

            if (username == null || password == null) {
                throw new AuthenticationException("Username or password not specified");
            }

            if (type == null) {
                throw new AuthenticationException("User type not specified");
            }

            UserType userType = UserType.valueOf(type);

            Login login = userService.login(username, password, userType);

            if (login == null) {
                throw new AuthenticationException("Wrong username or password");
            }

            return login;
        } catch (Exception exception) {
            throw exception;
        }
    }

    private void setCharacterEncoding(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
    }
}
