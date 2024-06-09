package com.webProgramming.controllers;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/seller")
public class SellerServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        req.setAttribute("type", "SELLER");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/loginPage.jsp");
        dispatcher.forward(req, resp);
    }

    public static boolean testClientExist(String username, String password) {
        //edw benei stin vasei kanei eleghw kai epistrefei an yparhei o client
        return true;
    }
    public static boolean testClientExist(String AFM) {
        //edw benei stin vasei kanei eleghw kai epistrefei an yparhei o client
        return true;
    }
}
