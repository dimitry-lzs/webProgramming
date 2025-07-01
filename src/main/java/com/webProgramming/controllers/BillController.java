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
import com.webProgramming.models.Bill;
import com.webProgramming.models.Client;
import com.webProgramming.models.PhoneNumber;
import com.webProgramming.models.Program;
import com.webProgramming.models.Seller;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.services.BillService;
import com.webProgramming.services.CallService;
import com.webProgramming.services.PhoneNumberService;
import com.webProgramming.services.UserService;
import com.webProgramming.src.ClientPages;
import com.webProgramming.src.Generic;
import com.webProgramming.src.Login;
import com.webProgramming.src.SellerPages;

@WebServlet("/bills")
public class BillController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String BILL_ISSUE = "issue";
    private static final String BILL_ID_PROPERTY = "billID";

    private static final String CLIENT_ID_PARAM = "client_id";
    private static final String PHONE_NUMBER_PARAM = "phonenumber";
    private static final String TOTAL_COST_PARAM = "totalCost";
    private static final String PROGRAM_NAME_PARAM = "program_name";
    private static final String CALL_DURATION_PARAM = "callDuration";
    private static final String SELECTED_MONTH = "selectedmonthint";

    private final UserService userService;
    private final BillService billService;
    private final CallService callService;
    private final PhoneNumberService phoneNumberService;

    public BillController() {
        this.userService = new UserService();
        this.billService = new BillService();
        this.callService = new CallService();
        this.phoneNumberService = new PhoneNumberService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            Login loggedInUser = validateSession(request);
            handleGetRequest(request, response, loggedInUser);
        } catch (Exception exception) {
            handleException(request, response, exception);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            validateSession(request);
            verifySeller(request);

            // Get Bill attributes
            String month = request.getParameter(SELECTED_MONTH);
            String client_id = request.getParameter(CLIENT_ID_PARAM);
            String phonenumber = request.getParameter(PHONE_NUMBER_PARAM);
            String totalCost = request.getParameter(TOTAL_COST_PARAM);
            String program_name = request.getParameter(PROGRAM_NAME_PARAM);
            int call_duration = request.getParameter(CALL_DURATION_PARAM) == null ? 0
                    : Integer.parseInt(request.getParameter(CALL_DURATION_PARAM));
            Boolean paid = false;

            // Check
            if (month == null || client_id == null || phonenumber == null || paid == null || totalCost == null) {
                throw new IllegalArgumentException("Missing parameters:" + "month=" + month + "id=" + client_id
                        + "phonenumber=" + phonenumber + "totalCost=" + totalCost);
            }

            // Cast to correct types, so we can successfully store bill object.
            Client client = (Client) userService.findById(client_id, UserType.CLIENT);
            PhoneNumber phonenumberObj = phoneNumberService.findByNumber(phonenumber);

            // Create Bill object
            Bill bill = new Bill(client, phonenumberObj, Integer.parseInt(month), paid, Double.parseDouble(totalCost));
            bill.setCallDuration(call_duration);
            bill.setProgramName(program_name);

            // Save Bill
            billService.saveBill(bill);

            Map<String, Object> attributes = Map.of(
                    "link", request.getContextPath() + SellerPages.SELLER_MENU,
                    "message", "Bill issued successfully",
                    "title", "Success");

            ServletUtils.forwardToPage(request, response, Generic.SUCCESS, attributes);
        } catch (Exception exception) {
            handleException(request, response, exception);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            validateSession(request);
            Client client = verifyClient(request);

            BufferedReader reader = request.getReader();
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            String requestBody = stringBuilder.toString();

            JSONObject updatedData = new JSONObject(requestBody);

            int billID = Integer.parseInt(updatedData.getString(BILL_ID_PROPERTY));

            // Get the bill from DB.
            Bill bill = billService.findByID(billID); // This parameter came from "View Bill Details by Client"
            // !!!CHECK IF OLD BILL IS ALREADY PAID!!!
            if (bill.isPaid() == false) {
                // Update
                billService.payBill(bill.getID(), client.getId());
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Bill paid successfully!");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Bill already paid");
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(exception.getMessage());
        }
    }

    private void handleGetRequest(HttpServletRequest request, HttpServletResponse response, Login loggedInUser)
            throws Exception {
        switch (loggedInUser.getType()) {
            case SELLER:
                handleSellerGetRequest(request, response, verifySeller(request));
                break;
            case CLIENT:
                handleClientGetRequest(request, response, (Client) loggedInUser.getUser());
                break;
            default:
                throw new AuthenticationException("Permission denied.");
        }
    }

    private void handleClientGetRequest(HttpServletRequest request, HttpServletResponse response, Client client) {
        String billID = request.getParameter("billID");
        List<Bill> bills = null;
        Map<String, Object> attributes = new HashMap<>();

        if (billID == null) { // if billID is null, it means we want to show all bills of the client
            bills = billService.getClientsBills(client);
            attributes.put("client", client);
            attributes.put("bills", bills);
            ServletUtils.forwardToPage(request, response, ClientPages.VIEW_BILLS, attributes);
        } else { // if billID is not null, it means we want to show a specific bill
            int billIdInt = Integer.parseInt(request.getParameter("billID"));
            Bill bill = billService.findByID(billIdInt);
            attributes.put("bill", bill);
            ServletUtils.forwardToPage(request, response, ClientPages.BILL_DETAILS, attributes);
        }
    }

    private void handleSellerGetRequest(HttpServletRequest request, HttpServletResponse response, Seller loggedInSeller)
            throws Exception {
        String ClientID = request.getParameter("clientId");
        Client client = (Client) userService.findById(ClientID, UserType.CLIENT);

        if (client == null) {
            throw new ServletException("Client not found");
        }

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("clientID", ClientID);
        attributes.put("client", client);

        String action = request.getParameter("action");

        if (action != null && action.equals(BillController.BILL_ISSUE)) {

            PhoneNumber phoneNumber = client.getPhoneNumber();

            if (phoneNumber == null) {
                throw new IllegalArgumentException("Client has no phone number");
            }

            int totalCallDuration = callService.totalCallDuration(phoneNumber);

            Program program = client.getPhoneNumber().getProgram();

            if (program == null) {
                throw new IllegalArgumentException("Client has no program");
            }

            int getChargePerSecond = program.getChargePerSecond();
            int fee = program.getFee();
            int callTime = program.getCallTime();
            int amount = (totalCallDuration - callTime) * getChargePerSecond < 0 ? fee
                    : (totalCallDuration - callTime) * getChargePerSecond + fee;

            attributes.put("totalCallDuration", totalCallDuration);
            attributes.put("amount", amount);
            ServletUtils.forwardToPage(request, response, SellerPages.ISSUE_BILL, attributes);
        } else {
            String billID = request.getParameter("billID");
            if (billID == null) { // if billID is null, it means we want to show all bills of the client
                List<Bill> bills = null;
                bills = billService.getClientsBills(client);
                attributes.put("bills", bills);
                ServletUtils.forwardToPage(request, response, SellerPages.VIEW_CLIENT_BILLS, attributes);
            } else { // if billID is not null, it means we want to show a specific bill
                int billIDInt = Integer.parseInt(request.getParameter("billID"));
                Bill bill = billService.findByID(billIDInt);
                attributes.put("bill", bill);
                ServletUtils.forwardToPage(request, response, SellerPages.BILL_DETAILS, attributes);
            }
        }
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, Exception exception)
            throws ServletException, IOException {
        exception.printStackTrace();
        String link = request.getContextPath() + Generic.INDEX_PAGE;
        Login loggedInUser = (Login) request.getSession().getAttribute("user");

        if (loggedInUser != null) {
            link = request.getContextPath() + loggedInUser.getType().getRedirectPath();
        }

        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("errorMessage", exception.getMessage());
        errorAttributes.put("link", link);

        ServletUtils.forwardToPage(request, response, Generic.ERROR_PAGE, errorAttributes);
    }

    private Login validateSession(HttpServletRequest request) {
        Login loggedInUser = (Login) request.getSession().getAttribute("user");

        if (loggedInUser == null) {
            throw new SecurityException("You are not logged in.");
        }
        return loggedInUser;
    }

    private Seller verifySeller(HttpServletRequest request) {
        Login loggedInUser = validateSession(request);

        if (loggedInUser.getType() != UserType.SELLER) {
            throw new SecurityException("Permission denied.");
        }

        return (Seller) loggedInUser.getUser();
    }

    private Client verifyClient(HttpServletRequest request) {
        Login loggedInUser = validateSession(request);

        if (loggedInUser.getType() != UserType.CLIENT) {
            throw new SecurityException("Permission denied.");
        }

        return (Client) loggedInUser.getUser();
    }
}
