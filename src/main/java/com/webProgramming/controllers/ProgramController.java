package com.webProgramming.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webProgramming.daos.ProgramDao;
import com.webProgramming.models.Admin;
import com.webProgramming.models.Program;
import com.webProgramming.models.Seller;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.src.Login;

@WebServlet("/programs")
public class ProgramController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String redirectLink = request.getContextPath() + "/login.jsp";

        try {
            // Get and open session.
            Login logged = (Login) request.getSession().getAttribute("user");
            List<Program> programs = null;
            ProgramDao programDao = new ProgramDao();

            if (logged != null && logged.getType() == UserType.SELLER){
                redirectLink = request.getContextPath() + "/seller/menu.jsp";

                Seller seller = (Seller) logged.getUser();
                programs = programDao.DataProgramList(seller, UserType.SELLER);

                request.setAttribute("programs", programs);
                request.getRequestDispatcher("seller/ProgramsList.jsp").forward(request, response);

            } else if (logged != null && logged.getType() == UserType.ADMIN){
                redirectLink = request.getContextPath() + "/admin/menu.jsp";
                
                Admin admin = (Admin) logged.getUser();

                String id = request.getParameter("id");
                
                // If id program not null, show program details
                if (id != null) {
                    Program program = programDao.findById(id);
                    
                    request.setAttribute("program", program);
                    request.getRequestDispatcher("admin/ChaingeProgram.jsp").forward(request, response);
                } else { //else show list of programs
                    programs = programDao.DataProgramList(admin, UserType.ADMIN);

                    request.setAttribute("programs", programs);
                    request.getRequestDispatcher("admin/ProgramsList.jsp").forward(request, response);
                }

            }else {
                throw new SecurityException("Permission denied.");
            }

        }

        catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("link", redirectLink);
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String redirectLink = request.getContextPath() + "/admin/menu.jsp";
        try {
            // Get and open session.
            Login loggedInAdmin = (Login) request.getSession().getAttribute("user");

            if (loggedInAdmin == null || loggedInAdmin.getType() != UserType.ADMIN){
                redirectLink = request.getContextPath() + "/login.jsp";
                throw new SecurityException("Permission denied.");
            }

            Admin admin = (Admin) loggedInAdmin.getUser();

            String programName = request.getParameter("programName");
            Integer callTime = Integer.parseInt(request.getParameter("callTime"));
            Integer fee = Integer.parseInt(request.getParameter("fee"));
            Integer chargePerSecond = Integer.parseInt(request.getParameter("charge"));
            String benefits = request.getParameter("benefits");

            Program program = new Program(programName, callTime, fee, chargePerSecond);
            program.setBenefits(benefits);
            program.setAdmin(admin);
            
            ProgramDao programDao = new ProgramDao();

            String option = request.getParameter("option");
            Boolean programCrOrUb = false;

            if (option.equals("Ubdate_Program")){
                Program editProgram = programDao.findById(request.getParameter("id"));

                programCrOrUb = programDao.updateProgram(editProgram, program);

            }
            else if (option.equals("Create_Program")) {
                programCrOrUb = programDao.createProgram(program);   
            }

            if (!programCrOrUb) {
                throw new IllegalArgumentException("Program creation or update failed.");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
            request.setAttribute("link", request.getContextPath() + "/admin/menu.jsp");
            request.setAttribute("message", "New program created or update successfully.");
            request.setAttribute("title", "Success");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("link", redirectLink);
            dispatcher.forward(request, response);
        }
    }
}
