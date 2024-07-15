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

import com.webProgramming.daos.BillDao;
import com.webProgramming.daos.CallDao;
import com.webProgramming.daos.PhoneNumberDao;
import com.webProgramming.daos.UserDao;
import com.webProgramming.models.Bill;
import com.webProgramming.models.Client;
import com.webProgramming.models.PhoneNumber;
import com.webProgramming.models.Program;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.src.Login;

@WebServlet("/bills")
public class BillController extends HttpServlet {
       @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Login loggedInUser = (Login) request.getSession().getAttribute("user");

        try{
            if (loggedInUser == null) {
                throw new SecurityException("Permission denied.");
            }

            List<Bill> bills = null;
            BillDao billdao = new BillDao();
            UserDao userDao = new UserDao();

            switch (loggedInUser.getType()) {
                case UserType.SELLER: {
                    String ClientID = request.getParameter("clientId");
                    Client client = (Client)userDao.findById(ClientID, UserType.CLIENT);

                    if (client == null) {
                        throw new IllegalArgumentException("Client not found");
                    }

                    request.setAttribute("client", client);
                    String action = request.getParameter("action");

                    if (action.equals("issue")) {
                        CallDao callDao = new CallDao();

                        PhoneNumber phoneNumber = client.getPhoneNumber();

                        if (phoneNumber == null) {
                            throw new IllegalArgumentException("Client has no phone number");
                        }

                        int totalCallDuration = callDao.totalCallDuration(phoneNumber);

                        Program program = client.getPhoneNumber().getProgram();

                        if (program == null) {
                            throw new IllegalArgumentException("Client has no program");
                        }

                        int getChargePerSecond = program.getChargePerSecond();
                        int fee = program.getFee();
                        int callTime = program.getCallTime();
                        int amount = (totalCallDuration - callTime) * getChargePerSecond < 0 ? fee : (totalCallDuration - callTime) * getChargePerSecond + fee;

                        request.setAttribute("amount", amount);
                        request.setAttribute("totalCallDuration", totalCallDuration);
                        request.getRequestDispatcher("seller/IssueBill.jsp").forward(request, response);
                   
                        
                    } else if (action.equals("show")) {

                        String billID = request.getParameter("billID") ;
                        if(billID==null){ //if billID is null, it means we want to show all bills of the client
                            bills = billdao.viewClientsBills(client);
                            request.setAttribute("client", client);
                            request.setAttribute("bills", bills);
                            request.getRequestDispatcher("seller/ViewClientBills.jsp").forward(request, response);
                        }else{ //if billID is not null, it means we want to show a specific bill
                            Bill bill = billdao.findByID( billID);
                            request.setAttribute("client", client);
                            request.setAttribute("bill", bill);
                            request.getRequestDispatcher("seller/BillDetails.jsp").forward(request, response);

                        }
                        
                    }

                    break;
                }
                case UserType.CLIENT: {
                    Client client = (Client) loggedInUser.getUser();
                    String billID = request.getParameter("billID");
                    
                    if(billID==null){ //if billID is null, it means we want to show all bills of the client
                        bills = billdao.viewClientsBills(client);
                        request.setAttribute("client", client);
                        request.setAttribute("bills", bills);
                        request.getRequestDispatcher("client/ViewBills.jsp").forward(request, response);
                    }else{ //if billID is not null, it means we want to show a specific bill
                        Bill bill = billdao.findByID( billID);
                        request.setAttribute("bill", bill);
                        request.getRequestDispatcher("client/BillDetails.jsp").forward(request, response);

                    }
                    break;
                }
                default:
                    throw new SecurityException("Permission denied.");
            }
       } catch(Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());

            String redirectLink = request.getContextPath();

            if (loggedInUser == null) {
                redirectLink += "/login.jsp";
            } else {
                switch (loggedInUser.getType()) {
                    case UserType.CLIENT: {
                        redirectLink += "/client/menu.jsp";
                        break;
                    }
                    case UserType.SELLER: {
                        redirectLink += "/seller/menu.jsp";
                        break;
                    }
                    default: {
                        redirectLink += "/login.jsp";
                    }
                }
            }

            request.setAttribute("link", redirectLink);
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


            //Get Bill attributes
            String billID=request.getParameter("billID");
            String month = request.getParameter("selectedmonthint");
            String client_id = request.getParameter("client_id");
            String phonenumber = request.getParameter("phonenumber");
            String totalCost=request.getParameter("totalCost");
            String program_name=request.getParameter("program_name");
            int call_duration = request.getParameter("callDuration") == null ? 0 : Integer.parseInt(request.getParameter("callDuration"));
            Boolean paid = false;

            //Check
            if (month == null || client_id == null || phonenumber == null || paid == null || totalCost == null) {
                throw new IllegalArgumentException("Missing parameters:" + "month=" + month  + "id=" + client_id  + "phonenumber=" + phonenumber + "totalCost=" + totalCost);
            }

            //Cast to correct types, so we can successfully store bill object.
            UserDao userDao = new UserDao();
            PhoneNumberDao phonenumberDao = new PhoneNumberDao();
            Client client = (Client) userDao.findById(client_id, UserType.CLIENT);
            PhoneNumber phonenumberObj = phonenumberDao.findByNumber(phonenumber);

            //Create Bill object
            Bill bill = new Bill(client, phonenumberObj, Integer.parseInt(month), paid,Double.parseDouble(totalCost));
            bill.setCallDuration(call_duration);
            bill.setProgramName(program_name);
            //Save Bill
            BillDao billDao = new BillDao();
            boolean created = billDao.saveBill(bill);

            if (!created) {
                throw new IllegalArgumentException("Bill could not be created");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
            request.setAttribute("link", request.getContextPath() + "/seller/menu.jsp");
            request.setAttribute("message", "Bill issued successfully");
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

     @Override 
     protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("PUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUT");
        System.out.println("PUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUT");
        System.out.println("PUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUT");
        System.out.println("PUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUTPUT");
        String redirectLink = request.getContextPath() + "/client/ViewBills.jsp";
        Login loggedInUser = (Login) request.getSession().getAttribute("user");

        try{
             //Get client
             Login loggedInClient = (Login) request.getSession().getAttribute("user");

             if (loggedInClient == null || loggedInClient.getType() != UserType.CLIENT) {
                 redirectLink = request.getContextPath() + "/login.jsp";
                 throw new SecurityException("Permission denied.");
             }
            BillDao billdao = new BillDao();

            Client client = (Client)loggedInClient.getUser();
                    
            if (client == null) {
                throw new IllegalArgumentException("Client not found");
            }

            BufferedReader reader = request.getReader();
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String requestBody = stringBuilder.toString();

            JSONObject updatedData = new JSONObject(requestBody);

            String billID = updatedData.getString("billID");


            //Get the bill from DB.
            Bill bill = billdao.findByID(billID);    //This parameter came from "View Bill Details by Client"
            //!!!CHECK IF OLD BILL IS ALREADY PAID!!!
            if (bill.isPaid() == false) {
                //Update
                boolean success = billdao.updateBill(bill);
                if (success) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
                    request.setAttribute("link", request.getContextPath() + "/client/menu.jsp");
                    request.setAttribute("message", "Bill paid successfully!");
                    request.setAttribute("title", "Success");
                    dispatcher.forward(request, response);
                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
                    request.setAttribute("errorMessage", "Failed to paid Bill.");
                    request.setAttribute("link", request.getContextPath() + "/client/menu.jsp");
                    dispatcher.forward(request, response);
                }
            }
            else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
                request.setAttribute("errorMessage", "Bill has already been paid.");
                request.setAttribute("link", request.getContextPath() + "/client/menu.jsp");
                dispatcher.forward(request, response);
            }             
           
            
            
        } 
        catch(Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());

           

            if (loggedInUser == null) {
                redirectLink += "/login.jsp";
            } else {
                switch (loggedInUser.getType()) {
                    case UserType.CLIENT: {
                        redirectLink += "/client/menu.jsp";
                        break;
                    }
                    case UserType.SELLER: {
                        redirectLink += "/seller/menu.jsp";
                        break;
                    }
                    default: {
                        redirectLink += "/login.jsp";
                    }
                }
            }

            request.setAttribute("link", redirectLink);
            dispatcher.forward(request, response);
       }
     }
}

