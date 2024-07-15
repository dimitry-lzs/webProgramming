package com.webProgramming.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webProgramming.daos.ProgramDao;
import com.webProgramming.daos.UserDao;
import com.webProgramming.models.Client;
import com.webProgramming.models.Program;
import com.webProgramming.models.Seller;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.src.Login;


@WebServlet("/clients")
public class ClientController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProgramDao programDao = new ProgramDao();
        String redirectLink = request.getContextPath() + "/seller/menu.jsp";

        try {
            // Get and open session.
            Login loggedInSeller = (Login) request.getSession().getAttribute("user");

            if (loggedInSeller == null || loggedInSeller.getType() != UserType.SELLER) {
                redirectLink = request.getContextPath() + "/login.jsp";
                throw new SecurityException("Permission denied.");
            }

            String id = request.getParameter("id");


            Seller seller = (Seller) loggedInSeller.getUser();
            UserDao userDao = new UserDao();
            userDao.reloadUser(seller);

            if (id != null) {
                Set<Client> clients = seller.getClients();
                Client client = null;

                for (Client c : clients) {
                    if (c.getId() == Integer.parseInt(id)) {
                        client = c;
                        break;
                    }
                }

                if (client == null) {
                    throw new IllegalArgumentException("Client not found");
                }

                List<Program> programs = programDao.DataProgramList(seller, UserType.SELLER);

                request.setAttribute("programs", programs);
                request.setAttribute("client", client);

                //Test
                request.setAttribute("referer", request.getHeader("referer"));
                if (request.getParameter("fromjsp").equals("list")){
                    request.getRequestDispatcher("seller/ClientDetails.jsp").forward(request, response);
                }
                else if (request.getParameter("fromjsp").equals("clientdetails")) {
                    request.getRequestDispatcher("seller/ViewClientBills.jsp").forward(request, response);
                }
                else if (request.getParameter("fromjsp").equals("viewclientbills")) {
                    request.getRequestDispatcher("seller/SelectClientBillMonth.jsp").forward(request, response);
                }

                //This might be useless.
                else if (request.getParameter("fromjsp").equals("monthselect")) {
                    request.setAttribute("SelectedMonthInt", request.getParameter("selectedmonthint"));
                    request.setAttribute("SelectedMonthText", monthIntToText(Integer.parseInt(request.getParameter("selectedmonthint"))));
                    request.getRequestDispatcher("seller/IssueBill.jsp").forward(request, response);
                }
                else {
                    String er = "Error from value:" + request.getParameter("fromjsp");
                    request.setAttribute("errorMessage", er);
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }


            } else {
                Set<Client> clients = seller.getClients();
                request.setAttribute("clients", clients);
                request.getRequestDispatcher("seller/ClientsList.jsp").forward(request, response);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("link", redirectLink);
            dispatcher.forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String redirectLink = request.getContextPath() + "/seller/menu.jsp";

        try {
            Login loggedInSeller = (Login) request.getSession().getAttribute("user");

            if (loggedInSeller == null || loggedInSeller.getType() != UserType.SELLER) {
                redirectLink = request.getContextPath() + "/login.jsp";
                throw new SecurityException("Permission denied.");
            }

            Seller seller = (Seller) loggedInSeller.getUser();

            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String afm = request.getParameter("afm");

            if (name == null || surname == null || username == null || password == null || afm == null || seller == null || confirmPassword == null) {
                throw new IllegalArgumentException("Missing parameters");
            }

            if (!password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Passwords do not match");
            }

            Client client = seller.createClient(afm, username, name, surname, password);

            UserDao userDao = new UserDao();
            boolean created = userDao.saveUser(client);

            if (!created) {
                throw new IllegalArgumentException("Client could not be created");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
            request.setAttribute("link", request.getContextPath() + "/seller/menu.jsp");
            request.setAttribute("message", "Client created successfully");
            request.setAttribute("title", "Success");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("link", redirectLink);
            dispatcher.forward(request, response);
        }
    }

    private String monthIntToText(int num) {
        String monthText = null;
        if (num == 1) {
            monthText = "January";
        }
        else if (num == 2)  {
            monthText = "February";
        }
        else if (num == 3)  {
            monthText = "March";
        }
        else if (num == 4)  {
            monthText = "April";
        }
        else if (num == 5)  {
            monthText = "May";
        }
        else if (num == 6)  {
            monthText = "June";
        }
        else if (num == 7)  {
            monthText = "July";
        }
        else if (num == 8)  {
            monthText = "August";
        }
        else if (num == 9)  {
            monthText = "September";
        }
        else if (num == 10)  {
            monthText = "Octorber";
        }
        else if (num == 11)  {
            monthText = "November";
        }
        else if (num == 12)  {
            monthText = "December";
        }
        return monthText;
    }
}
