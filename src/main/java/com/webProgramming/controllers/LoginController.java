package com.webProgramming.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webProgramming.daos.UserDao;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.src.Login;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    static final UserDao userDao = new UserDao();
    static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            String type = request.getParameter("type");

            if (type == null) {
                throw new Exception("User type not specified");
            }

            UserType userType = UserType.valueOf(type);

            HttpSession session = request.getSession();

            Login login = userDao.login(username, password, userType);
            String redirectPath = "";

            if (login == null) {
                redirectPath = "/loginError.jsp";
            } else {
                session.setAttribute("user", login);
                switch (userType) {
                    case ADMIN:
                        redirectPath = "/admin/menu.jsp";
                        break;
                    case SELLER:
                        redirectPath = "/seller/menu.jsp";
                        break;
                    case CLIENT:
                        redirectPath = "/client/menu.jsp";
                        break;
                }
            }

            RequestDispatcher next = request.getRequestDispatcher(redirectPath);
            next.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            dispatcher.forward(request, response);
        }
    }
}
