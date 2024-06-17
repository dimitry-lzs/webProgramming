package com.webProgramming.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import org.hibernate.query.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.webProgramming.daos.UserDao;
import com.webProgramming.models.Client;
import com.webProgramming.models.Program;
import com.webProgramming.models.Seller;
import com.webProgramming.models.Util;
import com.webProgramming.src.Login;
import com.webProgramming.models.enums.UserType;

@WebServlet("/clients")
public class ClientController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
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
            session.refresh(seller);

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

                String hql = "SELECT p FROM Program p WHERE p.admin.id = :adminId";
                Query<Program> query = session.createQuery(hql, Program.class);

                query.setParameter("adminId", seller.getAdmin().getId());
                List<Program> programs = query.list();

                request.setAttribute("programs", programs);
                request.setAttribute("client", client);
                request.getRequestDispatcher("seller/ClientDetails.jsp").forward(request, response);
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

        finally {
            // Close session.
            session.close();
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

            Client client = seller.createClient(afm, username, username, surname, password);

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
            dispatcher.forward(request, response);;
        }
    }
}
