package com.webProgramming.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webProgramming.models.Call;
import com.webProgramming.models.Client;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.services.CallService;
import com.webProgramming.src.ClientPages;
import com.webProgramming.src.Generic;
import com.webProgramming.src.Login;

@WebServlet("/calls")
public class CallController extends HttpServlet {
    static final long serialVersionUID = 1L;
    private final CallService callService;

    public CallController() {
        this.callService = new CallService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            Client client = verifyClient(request);

            List<Call> calls = callService.calls(client.getPhoneNumber());
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("calls", calls);
            ServletUtils.forwardToPage(request, response, ClientPages.VIEW_CALL_HISTORY, attributes);
        } catch (Exception exception) {
            handleException(request, response, exception);
        }
    }

    private Client verifyClient(HttpServletRequest request) {
        Login login = (Login) request.getSession().getAttribute("user");

        if (login == null) {
            throw new SecurityException("You are not logged in.");
        }

        if (login.getType() != UserType.CLIENT) {
            throw new SecurityException("Permission denied.");
        }

        return (Client) login.getUser();
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, Exception exception) throws ServletException, IOException {
        exception.printStackTrace();
        String link = request.getContextPath() + UserType.CLIENT.getRedirectPath();
        Map <String, Object> attributes = new HashMap<>();
        attributes.put("errorMessage", exception.getMessage());
        attributes.put("link", link);
        ServletUtils.forwardToPage(request, response, Generic.ERROR_PAGE, attributes);
    }
}
