package com.webProgramming.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.webProgramming.daos.PhoneNumberDao;
import com.webProgramming.daos.ProgramDao;
import com.webProgramming.models.PhoneNumber;
import com.webProgramming.models.Program;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.src.Login;

@WebServlet("/phonenumbers")
public class PhoneController extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Login loggedInSeller = (Login) request.getSession().getAttribute("user");

            if (loggedInSeller == null) {
                response.sendRedirect("/login.jsp");
                return;
            }

            if (loggedInSeller.getType() != UserType.SELLER) {
                throw new IllegalArgumentException("Only seller can update phone numbers");
            }

            BufferedReader reader = request.getReader();
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String requestBody = stringBuilder.toString();

            JSONObject updatedData = new JSONObject(requestBody);

            String number = updatedData.getString("number");

            PhoneNumberDao phoneNumberDao = new PhoneNumberDao();
            PhoneNumber phoneNumber = phoneNumberDao.findByNumber(number);

            if (phoneNumber == null) {
                throw new IllegalArgumentException("Phone number not found");
            }

            ProgramDao programDao = new ProgramDao();

            String programId = updatedData.getString("program_id");

            Program program = programDao.findById(programId);

            phoneNumber.setProgram(program);
            phoneNumberDao.updatePhoneNumber(phoneNumber);

            RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
            request.setAttribute("message", "Phone number created successfully");
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
