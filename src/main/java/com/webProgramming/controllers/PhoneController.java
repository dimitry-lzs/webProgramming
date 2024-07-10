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
import com.webProgramming.daos.UserDao;
import com.webProgramming.models.Client;
import com.webProgramming.models.PhoneNumber;
import com.webProgramming.models.Program;
import com.webProgramming.models.Seller;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.src.Login;

@WebServlet("/phonenumbers")
public class PhoneController extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Login loggedInSeller = (Login) request.getSession().getAttribute("user");

            if (loggedInSeller == null || loggedInSeller.getType() != UserType.SELLER) {
                String redirectLink = request.getContextPath() + "/seller/menu.jsp";
                redirectLink = request.getContextPath() + "/login.jsp";
                request.getRequestDispatcher(redirectLink).forward(request, response);
                return;
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

            if (program == null) {
                throw new IllegalArgumentException("Program not found");
            }

            Seller seller = (Seller) loggedInSeller.getUser();
            UserDao userDao = new UserDao();
            userDao.reloadUser(seller);

            boolean phoneBelongsToSeller = false;

            for (Client client : seller.getClients()) {
                System.out.println(client.getPhoneNumber().getNumber());
                if (client.getPhoneNumber().getNumber().equals(phoneNumber.getNumber())) {
                    phoneBelongsToSeller = true;
                    break;
                }
            }

            if (!phoneBelongsToSeller) {
                throw new IllegalArgumentException("You can only update phone numbers for your clients");
            }

            if (program.getAdmin().getId() != seller.getAdmin().getId()) {
                throw new IllegalArgumentException("You can only update phone numbers for your programs");
            }

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
