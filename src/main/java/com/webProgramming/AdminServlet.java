package com.webProgramming;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<p style =\"font-size: 20px;\n" + //
                        "font-family: sans-serif;\" >Admin does'n exist</p>");
        writer.flush();
        writer.close();
    }
}
