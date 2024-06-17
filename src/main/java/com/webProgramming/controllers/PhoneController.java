package com.webProgramming.controllers;

import java.io.BufferedReader;
import java.io.IOException;

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
            boolean updated = phoneNumberDao.updatePhoneNumber(phoneNumber);

            if (!updated) {
                throw new IllegalArgumentException("Failed to update phone number");
            }

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Updated phone number successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(e.getMessage());
        }
    }
}
