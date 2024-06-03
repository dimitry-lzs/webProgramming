package com.webProgramming;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webProgramming.Classes.Admin;
import com.webProgramming.Classes.UserDao;

public class Test extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Hello World!!");
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            UserDao userDao = new UserDao();

            Admin admin = new Admin("admin", "admin", "admin");

            userDao.saveUser(admin);

            PrintWriter writer = response.getWriter();
            writer.println("<h1>Hello World!! PAPATZA, kai oxi mia alla polles! </h1>");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}