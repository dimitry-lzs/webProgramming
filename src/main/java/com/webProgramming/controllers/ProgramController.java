package com.webProgramming.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.webProgramming.exceptions.AuthenticationException;
import com.webProgramming.models.Admin;
import com.webProgramming.models.Program;
import com.webProgramming.models.Seller;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.services.ProgramService;
import com.webProgramming.src.AdminPages;
import com.webProgramming.src.Generic;
import com.webProgramming.src.Login;
import com.webProgramming.src.SellerPages;

@WebServlet("/programs")
public class ProgramController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProgramService programService;

    public ProgramController() {
        this.programService = new ProgramService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String link = request.getContextPath();

        try {
            Login loggedInUser = (Login) request.getSession().getAttribute("user");

            if (loggedInUser == null) {
                link = link + Generic.INDEX_PAGE;
                throw new AuthenticationException("You are not logged in.");
            }

            switch (loggedInUser.getType()) {
                case ADMIN:
                    handleAdminGetRequest(request, response);
                    break;
                case SELLER:
                    handleSellerGetRequest(request, response);
                    break;
                default:
                    link = link + loggedInUser.getType().getRedirectPath();
                    throw new AuthenticationException("Permission denied.");
            }
        }

        catch (Exception exception) {
            exception.printStackTrace();
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("errorMessage", exception.getMessage());
            attributes.put("link", link);
            ServletUtils.forwardToPage(request, response, Generic.ERROR_PAGE, attributes);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            Admin admin = verifyAdmin(request);

            String programName = request.getParameter("programName");
            Integer callTime = Integer.parseInt(request.getParameter("callTime"));
            Integer fee = Integer.parseInt(request.getParameter("fee"));
            Integer chargePerSecond = Integer.parseInt(request.getParameter("charge"));
            String benefits = request.getParameter("benefits");

            Program program = new Program(programName, callTime, fee, chargePerSecond);
            program.setBenefits(benefits);
            program.setAdmin(admin);

            programService.saveProgram(program);

            Map<String, Object> attributes = new HashMap<>();

            attributes.put("message", "New program created successfully.");
            attributes.put("link", request.getContextPath() + AdminPages.ADMIN_MENU);
            attributes.put("title", "Success");
            ServletUtils.forwardToPage(request, response, Generic.SUCCESS, attributes);

        } catch (Exception exception) {
            handlePostException(request, response, exception);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            Admin admin = verifyAdmin(request);

            String programId = request.getParameter("id");

            if (programId == null) {
                throw new IllegalArgumentException("Program id is required.");
            }

            Program program = programService.findById(programId);

            if (program == null) {
                throw new IllegalArgumentException("Program not found.");
            }

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

            programService.updateProgram(program);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Updated phone number successfully");
        } catch (Exception exception) {
            exception.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(exception.getMessage());
        }
    }

    private void handlePostException(HttpServletRequest request, HttpServletResponse response, Exception exception)
            throws ServletException, IOException {
        exception.printStackTrace();
        Login loggedInUser = (Login) request.getSession().getAttribute("user");
        String link = request.getContextPath() + Generic.INDEX_PAGE;

        if (loggedInUser != null) {
            link = request.getContextPath() + loggedInUser.getType().getRedirectPath();
        }

        System.out.println(link + " " + exception.getMessage() + " " + Generic.ERROR_PAGE);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("errorMessage", exception.getMessage());
        attributes.put("link", link);
        ServletUtils.forwardToPage(request, response, Generic.ERROR_PAGE, attributes);
    }

    private Admin verifyAdmin(HttpServletRequest request) {
        Login loggedInUser = (Login) request.getSession().getAttribute("user");

        if (loggedInUser == null) {
            throw new SecurityException("You are not logged in.");
        }

        if (loggedInUser.getType() != UserType.ADMIN) {
            throw new SecurityException("Permission denied.");
        }

        return (Admin) loggedInUser.getUser();
    }

    private Seller verifySeller(HttpServletRequest request) {
        Login loggedInUser = (Login) request.getSession().getAttribute("user");

        if (loggedInUser == null) {
            throw new SecurityException("You are not logged in.");
        }

        if (loggedInUser.getType() != UserType.SELLER) {
            throw new SecurityException("Permission denied.");
        }

        return (Seller) loggedInUser.getUser();
    }

    private void handleAdminGetRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Admin admin = verifyAdmin(request);

        String id = request.getParameter("id");
        List<Program> programs = null;
        Map<String, Object> attributes = new HashMap<>();

        if (id != null) {
            Program program = programService.findById(id);
            attributes.put("program", program);
            ServletUtils.forwardToPage(request, response, AdminPages.UPDATE_PROGRAM, attributes);
        } else {
            programs = programService.programs(admin, UserType.ADMIN);
            attributes.put("programs", programs);
            ServletUtils.forwardToPage(request, response, AdminPages.PROGRAMS_LIST, attributes);
        }
    }

    private void handleSellerGetRequest(HttpServletRequest request, HttpServletResponse response) {
        Seller seller = verifySeller(request);
        List<Program> programs = null;
        programs = programService.programs(seller, UserType.SELLER);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("programs", programs);
        ServletUtils.forwardToPage(request, response, SellerPages.PROGRAMS_LIST, attributes);
    }
}
