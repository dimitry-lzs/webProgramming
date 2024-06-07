package com.webProgramming;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.webProgramming.Classes.Client;
import com.webProgramming.Classes.Program;
import com.webProgramming.Classes.Util;


public class AssignProgramToClientServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        System.out.println("POSTING");

        //Get data that was entered by user.
        String programid = req.getParameter("newProgram");
        String AFM = req.getParameter("ClientAFM");

        //Get writer.
        PrintWriter writer = resp.getWriter();

        //Create ORM session.
        SessionFactory fac = Util.getSessionFactory();
        Session ses = fac.openSession();

        ses.beginTransaction();



        try {
            //Look for matching AFM and Program.
            writer.println("Finding AFM...");
            
            
           // java.util.List<Client> clients = ses.createQuery("select c from Client c where c.AFM = " + AFM, Client.class).list();
            
            writer.println("Finding Program...");
            java.util.List<Program> programs = ses.createQuery("select p from Program p where p.id = " + programid, Program.class).list();
            
            writer.println("Printing Result...");
            //if (clients.size() > 0 && programs.size() > 0) {
            if (programs.size() > 0) {
                writer.println("Found matching AFM and Program ID!");
            }
            else {
                writer.println("Could not find matching AFM and Program ID.");
            }
       
        }
        catch(HibernateException e) {
            writer.println(e);
        }


        ses.getTransaction().commit(); 
        writer.flush();
        writer.close();

    }

}

