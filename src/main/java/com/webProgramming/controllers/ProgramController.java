package com.webProgramming.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.webProgramming.daos.ProgramDao;
import com.webProgramming.models.Admin;
import com.webProgramming.models.Program;
import com.webProgramming.models.Seller;
import com.webProgramming.models.User;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.src.Login;

@WebServlet("/programs")
public class ProgramController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String redirectLink = request.getContextPath();

        try {
            Login logged = (Login) request.getSession().getAttribute("user");
            List<Program> programs = null;
            ProgramDao programDao = new ProgramDao();

            if (logged == null) {
                redirectLink = redirectLink + "/index.jsp";
                throw new SecurityException("You are not logged in.");
            }

            if (logged.getType() == UserType.SELLER) {
                redirectLink = redirectLink + "/seller/menu.jsp";

                Seller seller = (Seller) logged.getUser();
                programs = programDao.DataProgramList(seller, UserType.SELLER);

                request.setAttribute("programs", programs);
                request.getRequestDispatcher("seller/ProgramsList.jsp").forward(request, response);

            } else if (logged.getType() == UserType.ADMIN){
                redirectLink = redirectLink + "/admin/menu.jsp";

                Admin admin = (Admin) logged.getUser();

                String id = request.getParameter("id");

                if (id != null) {
                    Program program = programDao.findById(id);

                    request.setAttribute("program", program);
                    request.getRequestDispatcher("admin/UpdateProgram.jsp").forward(request, response);
                } else {
                    programs = programDao.DataProgramList(admin, UserType.ADMIN);

                    request.setAttribute("programs", programs);
                    request.getRequestDispatcher("admin/ProgramsList.jsp").forward(request, response);
                }

            } else {
                throw new SecurityException("Permission denied.");
            }
        }

        catch (Exception exception) {
            exception.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", exception.getMessage());
            request.setAttribute("link", redirectLink);
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String redirectLink = request.getContextPath() + "/admin/menu.jsp";
        try {
            Login loggedInUser = (Login) request.getSession().getAttribute("user");

            if (loggedInUser == null){
                redirectLink = request.getContextPath() + "/index.jsp";
                throw new SecurityException("You are not logged in.");
            }

            if (loggedInUser.getType() != UserType.ADMIN){
                redirectLink = request.getContextPath() + User.getRedirectionLink(loggedInUser.getType().name());
                throw new SecurityException("Permission denied.");
            }

            Admin admin = (Admin) loggedInUser.getUser();

            String programName = request.getParameter("programName");
            Integer callTime = Integer.parseInt(request.getParameter("callTime"));
            Integer fee = Integer.parseInt(request.getParameter("fee"));
            Integer chargePerSecond = Integer.parseInt(request.getParameter("charge"));
            String benefits = request.getParameter("benefits");

            Program program = new Program(programName, callTime, fee, chargePerSecond);
            program.setBenefits(benefits);
            program.setAdmin(admin);

            ProgramDao programDao = new ProgramDao();

            boolean programCreated = programDao.createProgram(program);

            if (!programCreated) {
                throw new IllegalArgumentException("Program creation failed.");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
            request.setAttribute("link", redirectLink);
            request.setAttribute("message", "New program created successfully.");
            request.setAttribute("title", "Success");
            dispatcher.forward(request, response);

        } catch (Exception exception) {
            exception.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", exception.getMessage());
            request.setAttribute("link", redirectLink);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String redirectLink = request.getContextPath() + "/admin/menu.jsp";

        try {
            Login loggedInUser = (Login) request.getSession().getAttribute("user");

            if (loggedInUser == null){
                redirectLink = request.getContextPath() + "/index.jsp";
                throw new SecurityException("You are not logged in.");
            }

            if (loggedInUser.getType() != UserType.ADMIN){
                redirectLink = request.getContextPath() + User.getRedirectionLink(loggedInUser.getType().name());
                throw new SecurityException("Permission denied.");
            }

            String programId = request.getParameter("id");

            if (programId == null) {
                throw new IllegalArgumentException("Program id is required.");
            }

            ProgramDao programDao = new ProgramDao();

            Program program = programDao.findById(programId);

            if (program == null) {
                throw new IllegalArgumentException("Program not found.");
            }

            Admin admin = (Admin) loggedInUser.getUser();

            if (program.getAdmin().getId() != (admin.getId())) {
                throw new SecurityException("Permission denied.");
            }

            BufferedReader reader = request.getReader();
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String requestBody = stringBuilder.toString();

            JSONObject updatedData = new JSONObject(requestBody);

            String programName = updatedData.getString("programName");
            int callTime = updatedData.getInt("callTime");
            int fee = updatedData.getInt("fee");
            int chargePerSecond = updatedData.getInt("charge");
            String benefits = updatedData.getString("benefits");

            program.setName(programName);
            program.setCallTime(callTime);
            program.setFee(fee);
            program.setChargePerSecond(chargePerSecond);
            program.setBenefits(benefits);

            boolean programUpdated = programDao.updateProgram(program);

            if (!programUpdated) {
                throw new IllegalArgumentException("Program update failed.");
            }

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Updated phone number successfully");
        } catch (Exception exception) {
            exception.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(exception.getMessage());
        }
    }
}
