package com.webProgramming.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webProgramming.daos.CallDao;
import com.webProgramming.models.Call;
import com.webProgramming.models.Client;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.src.Login;
@WebServlet("/calls")
public class CallControler extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CallDao callDao = new CallDao();
        String redirectLink = request.getContextPath() + "/client/menu.jsp";


        try {
            Login loggedInClient = (Login) request.getSession().getAttribute("user");

            if (loggedInClient == null || loggedInClient.getType() != UserType.CLIENT) {
                redirectLink = request.getContextPath() + "/login.jsp";
                throw new SecurityException("Permission denied.");
            }

            Client client = (Client) loggedInClient.getUser();
            //sdjbsjbhdnfalsbdkf
            
            //dao
            // Call call = new Call(client.getPhoneNumber(), client.getPhoneNumber(), 10, 15);
            // Session session = Util.getSessionFactory().openSession();
            // Transaction transaction = session.beginTransaction();
            // session.persist(call);
            // transaction.commit();


            List<Call> calls = callDao.DataCallList(client.getPhoneNumber());
            //sdafihoisdf

            request.setAttribute("calls", calls);
            request.getRequestDispatcher("client/viewCallHistory.jsp").forward(request, response);


        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("link", redirectLink);
            dispatcher.forward(request, response);
        }
    }

}
