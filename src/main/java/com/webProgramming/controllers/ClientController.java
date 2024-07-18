package com.webProgramming.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webProgramming.models.Client;
import com.webProgramming.models.Program;
import com.webProgramming.models.Seller;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.services.CallService;
import com.webProgramming.services.ProgramService;
import com.webProgramming.services.UserService;
import com.webProgramming.src.Generic;
import com.webProgramming.src.Login;
import com.webProgramming.src.SellerPages;


@WebServlet("/clients")
public class ClientController extends HttpServlet {
    static final long serialVersionUID = 1L;
    private final UserService userService;
    private final CallService callService;
    private final ProgramService programService;

    private final String NAME_PARAM = "name";
    private final String SURNAME_PARAM = "surname";
    private final String USERNAME_PARAM = "username";
    private final String PASSWORD_PARAM = "password";
    private final String CONFIRM_PASSWORD_PARAM = "confirmPassword";
    private final String AFM_PARAM = "afm";


    public ClientController() {
        this.userService = new UserService();
        this.callService = new CallService();
        this.programService = new ProgramService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Seller seller = verifySeller(request);
            String id = request.getParameter("id");
            userService.reloadUser(seller);

            Map<String, Object> attributes = new HashMap<>();

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

                List<Program> programs = programService.programs(seller, UserType.SELLER);

                attributes.put("programs", programs);
                attributes.put("client", client);

                ServletUtils.forwardToPage(request, response, SellerPages.CLIENT_DETAILS, attributes);
            } else {
                Set<Client> clients = seller.getClients();
                attributes.put("clients", clients);
                ServletUtils.forwardToPage(request, response, SellerPages.CLIENTS_LIST, attributes);
            }
        }

        catch (Exception exception) {
            handleGetException(request, response, exception);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        try {
            Seller seller = verifySeller(request);
            String link = request.getContextPath() + SellerPages.SELLER_MENU;
            Client client = createClientFromRequest(request, seller);

            callService.createClientWithCalls(client, 4);

            ServletUtils.forwardToPage(request, response, Generic.SUCCESS, Map.of(
                    "message", "Client created successfully",
                    "link", link,
                    "title", "Success"
            ));
        } catch (Exception exception) {
            handlePostException(request, response, exception);
        }
    }

    private void handleGetException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        exception.printStackTrace();

        String link = request.getContextPath() + Generic.INDEX_PAGE;

        Login loggedInUser = (Login) request.getSession().getAttribute("user");

        if (loggedInUser != null) {
            link = request.getContextPath() + loggedInUser.getType().getRedirectPath();
        }

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("errorMessage", exception.getMessage());
        attributes.put("link", link);
        ServletUtils.forwardToPage(request, response, Generic.ERROR_PAGE, attributes);
    }

    private void handlePostException(HttpServletRequest request, HttpServletResponse response, Exception exception) throws ServletException, IOException {
        exception.printStackTrace();
        Login loggedInUser = (Login) request.getSession().getAttribute("user");
        String link = request.getContextPath() + Generic.INDEX_PAGE;

        if (loggedInUser != null) {
            if (loggedInUser.getType() == UserType.SELLER) {
                link = request.getContextPath() + SellerPages.ADD_CLIENT;
            } else {
                link = request.getContextPath() + loggedInUser.getType().getRedirectPath();
            }
        }
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("errorMessage", exception.getMessage());
        attributes.put("link", link);
        ServletUtils.forwardToPage(request, response, Generic.ERROR_PAGE, attributes);
    }

    private Seller verifySeller(HttpServletRequest request) {
        Login loggedInUser = (Login) request.getSession().getAttribute("user");

        if (loggedInUser == null) {
            throw new SecurityException("You are not logged in.");
        }

        if (loggedInUser.getType() != UserType.SELLER) {
            throw new SecurityException("Permission denied.");
        }

        return (Seller) loggedInUser.getUser();
    }

    private Client createClientFromRequest(HttpServletRequest request, Seller seller) throws NoSuchAlgorithmException {
        String name = request.getParameter(NAME_PARAM);
        String surname = request.getParameter(SURNAME_PARAM);
        String username = request.getParameter(USERNAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD_PARAM);
        String afm = request.getParameter(AFM_PARAM);

        if (name == null || surname == null || username == null || password == null || afm == null) {
            throw new IllegalArgumentException("Missing parameters");
        }

        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        return seller.createClient(afm, username, name, surname, password);
    }
}
