package com.webProgramming.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webProgramming.models.UserDao;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.src.Login;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    static final UserDao userDao = new UserDao();
    static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<p style =\"font-size: 20px;\n" + //
                "font-family: sans-serif;\" >Login get</p>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");

            Login newLogin = new Login();

            newLogin.setUsername(request.getParameter("username"));
            newLogin.setPassword(request.getParameter("password"));

            String type = request.getParameter("type");

            if (type == null) {
                throw new Exception("User type not specified");
            }

            UserType userType = UserType.valueOf(type);
            newLogin.setType(userType);
            newLogin.setType(UserType.valueOf(request.getParameter("type")));

            HttpSession session = request.getSession();

            boolean success = userDao.login(newLogin.getUsername(), newLogin.getPassword(), newLogin.getType());

            String redirectPath = "";

            if (!success) {
                redirectPath = "/loginError.jsp";
            } else {
                session.setAttribute("username", newLogin.getUsername());
                session.setAttribute("type", newLogin.getType());

                switch (userType) {
                    case ADMIN:
                        redirectPath = "/admin/menu.jsp";
                        break;
                    case SELLER:
                        redirectPath = "/sellerMenu.jsp";
                        break;
                    case CLIENT:
                        redirectPath = "/customerMenu.jsp";
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
