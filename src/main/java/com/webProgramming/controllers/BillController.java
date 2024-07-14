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
import org.hibernate.mapping.Set;
import org.hibernate.query.Query;

import com.webProgramming.daos.BillDao;
import com.webProgramming.daos.PhoneNumberDao;
import com.webProgramming.daos.ProgramDao;
import com.webProgramming.daos.UserDao;
import com.webProgramming.models.Admin;
import com.webProgramming.models.Bill;
import com.webProgramming.models.Client;
import com.webProgramming.models.PhoneNumber;
import com.webProgramming.models.Program;
import com.webProgramming.models.Seller;
import com.webProgramming.models.User;
import com.webProgramming.models.Util;
import com.webProgramming.src.Login;

import com.webProgramming.models.enums.UserType;

@WebServlet("/bills")
public class BillController extends HttpServlet {
       @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       try{
            Login logged = (Login) request.getSession().getAttribute("user");
            List<Bill> bills = null;
            BillDao billdao = new BillDao();
            UserDao userDao = new UserDao();
            if (logged != null && logged.getType() == UserType.SELLER){

                String ClientID = request.getParameter("id");
                Client client = (Client)userDao.findById(ClientID,UserType.CLIENT);  
                bills = billdao.viewClientsBills(client);
                request.setAttribute("bills", bills);
                request.getRequestDispatcher("seller/ViewClientBills.jsp").forward(request, response);

            } else if (logged != null && logged.getType() == UserType.CLIENT){
                Client client = (Client) logged.getUser();
                bills = billdao.viewClientsBills(client);
                request.setAttribute("bills", bills);
                request.getRequestDispatcher("client/ViewBills.jsp").forward(request, response);

            }else {
                throw new SecurityException("Permission denied.");
            }
       } catch(Exception e){
           e.printStackTrace();
           RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
           request.setAttribute("errorMessage", e.getMessage());
           //request.setAttribute("link", request.getContextPath() + "/seller/menu.jsp"); //check this later
           dispatcher.forward(request, response);
       }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String redirectLink = request.getContextPath() + "/seller/menu.jsp";

        try {
            //Get seller
            Login loggedInSeller = (Login) request.getSession().getAttribute("user");

            if (loggedInSeller == null || loggedInSeller.getType() != UserType.SELLER) {
                redirectLink = request.getContextPath() + "/login.jsp";
                throw new SecurityException("Permission denied.");
            }

            Seller seller = (Seller) loggedInSeller.getUser();

            //Get Bill attributes
            String month = request.getParameter("selectedmonthint");
            String client_id = request.getParameter("client_id");
            String phonenumber = request.getParameter("phonenumber");
            Boolean paid = false;

            //Check
            if (month == null || client_id == null || phonenumber == null || paid == null) {
                throw new IllegalArgumentException("Missing parameters:" + "month=" + month  + "id=" + client_id  + "phonenumber=" + phonenumber);
            }

            //Cast to correct types, so we can successfully store bill object.
            UserDao userDao = new UserDao();
            PhoneNumberDao phonenumberDao = new PhoneNumberDao();
            Client client = (Client) userDao.findById(client_id, UserType.CLIENT);
            PhoneNumber phonenumberObj = phonenumberDao.findByNumber(phonenumber);

            //Create Bill object
            Bill bill = new Bill(client, phonenumberObj, Integer.parseInt(month), paid);
            
            //Save Bill
            BillDao billDao = new BillDao();
            boolean created = billDao.saveBill(bill);

            if (!created) {
                throw new IllegalArgumentException("Bill could not be created");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
            request.setAttribute("link", request.getContextPath() + "/seller/menu.jsp");
            request.setAttribute("message", "Bill created successfully");
            request.setAttribute("title", "Success");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("link", redirectLink);
            dispatcher.forward(request, response);;
        }
    }
}

