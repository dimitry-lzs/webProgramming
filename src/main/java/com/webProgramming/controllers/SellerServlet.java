package com.webProgramming.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/seller")
public class SellerServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<p style =\"font-size: 20px;\n" + //
                        "font-family: sans-serif;\" >Seller doesn't exist</p>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // Set an attribute that can be used in the JSP
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        String AFM = req.getParameter("afm");
        //login
        if (login(username, password)) {
            // an den yparxei o seller
            req.setAttribute("username", username);


            // Forward the request to the JSP
            RequestDispatcher dispatcher = req.getRequestDispatcher("/seller/Seller.jsp");
            dispatcher.forward(req, resp);
        } else if(testClientExist(username, password) && username != null && password != null){
            // prosthetoume ton Client
            PrintWriter writer = resp.getWriter();
            writer.println("<p style =\"font-size: 20px;\n" + //
                            "font-family: sans-serif;\" >Client insert</p>");
            writer.flush();
            writer.close();
        }else if(testClientExist(AFM) && AFM != null){
            // prosthetoume ton Client
            PrintWriter writer = resp.getWriter();
            writer.println("<p style =\"font-size: 20px;\n" + //
                            "font-family: sans-serif;\" >Client insert</p>");
            writer.flush();
            writer.close();
        }
    }

    public static boolean login(String username, String password) {
        //edw benei stin vasei kanei eleghw kai epistrefei an yparhei o seller
        return true;
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
