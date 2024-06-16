package com.webProgramming.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.webProgramming.daos.ProgramDao;
import com.webProgramming.models.Program;
import com.webProgramming.models.Util;
import com.webProgramming.src.Login;

import com.webProgramming.models.enums.UserType;

@WebServlet("/programs")
public class ProgramController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();

        try {
            // Get and open session.
            Login loggedInSeller = (Login) request.getSession().getAttribute("user");

            if (loggedInSeller == null || loggedInSeller.getType() != UserType.SELLER){
                throw new IllegalArgumentException("Permission denied.");
            }

            List<Program> programs = session.createQuery("select p from Program p", Program.class).list();

            request.setAttribute("programs", programs);
            request.getRequestDispatcher("seller/ProgramsList.jsp").forward(request, response);
        }

        catch (Exception e) {
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            // Get and open session.
            Login loggedInAdmin = (Login) request.getSession().getAttribute("user");

            if (loggedInAdmin == null || loggedInAdmin.getType() != UserType.ADMIN){
                throw new IllegalArgumentException("Permission denied.");
            }

            String programName = request.getParameter("programName");
            Integer callTime = Integer.parseInt(request.getParameter("callTime"));
            Integer fee = Integer.parseInt(request.getParameter("fee"));
            Integer chargePerSecond = Integer.parseInt(request.getParameter("charge"));
            String benefits = request.getParameter("benefits");

            Program program = new Program(programName, callTime, fee, chargePerSecond);

            program.setBenefits(benefits);
            ProgramDao programDao = new ProgramDao();

            boolean programCreated = programDao.createProgram(program);

            if (!programCreated) {
                throw new IllegalArgumentException("Program creation failed.");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");

            request.setAttribute("message", "New program created successfully.");
            request.setAttribute("title", "Success");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            dispatcher.forward(request, response);
        }
    }
}
