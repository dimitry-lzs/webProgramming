package com.webProgramming.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.webProgramming.models.Program;
import com.webProgramming.models.Util;
import com.webProgramming.src.Login;

import com.webProgramming.models.enums.UserType;

@WebServlet("/programs")
public class ProgramsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get and open session.

        Login loggedInSeller = (Login) request.getSession().getAttribute("user");

        if (loggedInSeller == null || loggedInSeller.getType() != UserType.SELLER){
            throw new IllegalArgumentException("Permission denied.");
        }

        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();

        try {
            List<Program> programs = session.createQuery("select p from Program p", Program.class).list();

            request.setAttribute("programs", programs);
            request.getRequestDispatcher("seller/ProgramsList.jsp").forward(request, response);
        }

        catch (HibernateException e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            dispatcher.forward(request, response);
        }

        finally {
            // Close session.
            session.close();
        }
    }
}
