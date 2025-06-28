package com.webProgramming.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.webProgramming.models.Client;
import com.webProgramming.models.PhoneNumber;
import com.webProgramming.models.Program;
import com.webProgramming.models.Seller;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.services.PhoneNumberService;
import com.webProgramming.services.ProgramService;
import com.webProgramming.services.UserService;
import com.webProgramming.src.Generic;
import com.webProgramming.src.Login;

@WebServlet("/phonenumbers")
public class PhoneController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserService userService;
    private final PhoneNumberService phoneNumberService;
    private final ProgramService programService;

    public PhoneController() {
        this.userService = new UserService();
        this.phoneNumberService = new PhoneNumberService();
        this.programService = new ProgramService();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectLink = request.getContextPath() + UserType.SELLER.getRedirectPath();
        try {
            Login loggedInSeller = (Login) request.getSession().getAttribute("user");

            if (loggedInSeller == null) {
                redirectLink = request.getContextPath() + Generic.INDEX_PAGE;
                request.getRequestDispatcher(redirectLink).forward(request, response);
                return;
            }

            if (loggedInSeller.getType() != UserType.SELLER) {
                redirectLink = request.getContextPath() + loggedInSeller.getType().getRedirectPath();
                throw new SecurityException("Permission denied.");
            }

            UserType.ADMIN.getRedirectPath();

            BufferedReader reader = request.getReader();
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String requestBody = stringBuilder.toString();

            JSONObject updatedData = new JSONObject(requestBody);

            String number = updatedData.getString("number");

            PhoneNumber phoneNumber = phoneNumberService.findByNumber(number);

            if (phoneNumber == null) {
                throw new IllegalArgumentException("Phone number not found");
            }

            String programId = updatedData.getString("program_id");

            Program program = programService.findById(programId);

            if (program == null) {
                throw new IllegalArgumentException("Program not found");
            }

            Seller seller = (Seller) loggedInSeller.getUser();
            userService.reloadUser(seller);

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
            phoneNumberService.updatePhoneNumber(phoneNumber);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Updated phone number successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(e.getMessage());
        }
    }
}
