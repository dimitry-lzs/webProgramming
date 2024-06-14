package com.webProgramming;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.descriptor.java.UUIDJavaType.ToStringTransformer;

import com.webProgramming.models.Client;
import com.webProgramming.models.PhoneNumber;
import com.webProgramming.models.Program;
import com.webProgramming.models.Util;

@WebServlet("/assignProgram")

public class AssignProgramToClientServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {}


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

            //This doesnt work 
            //java.util.List<Client> clients = ses.createQuery("select c from Client c where c.AFM = " + AFM, Client.class).list();

            String hql = "FROM Client Where AFM = :AFM";
            Query<Client> query = ses.createQuery(hql, Client.class);
            query.setParameter("AFM", AFM);
            java.util.List<Client> clients = query.list();


            writer.println("Finding Program...");
            java.util.List<Program> programs = ses.createQuery("select p from Program p where p.id = " + programid, Program.class).list();

            writer.println("Printing Result...");
            //if (clients.size() > 0 && programs.size() > 0) {
            if (clients.size() > 0 && programs.size() > 0) {
                writer.println("Found matching AFM and Program ID!");

                //Assign Program to Client.

                //Get client's Phonenumber
                Integer PhoneNumber = clients.get(0).getPhonenumber();  //Paizei na gyrisei san String?
                
                //Assign Program To Client's PhoneNumber
                boolean success = AssignProgramToNumber(programid, Integer.toString(clients.get(0).getPhonenumber()));

                if (success == true) {
                    writer.println("Program assignment was successful!");
                }
                else {
                    writer.println("Something went wrong. Could not assign program to Client.");
                }

            }
            else {
                writer.println("Could not find matching AFM and Program ID.");
            }

        }
        catch(HibernateException e) {
            writer.println(e);
        }


        //ses.getTransaction().commit();
        writer.flush();
        writer.close();

    }

    //Create or update an entry in the phonenumbers table.
    private boolean AssignProgramToNumber(String program_id, String phonenumber) {

        //Create ORM session.
        SessionFactory fac = Util.getSessionFactory();
        Session ses = fac.openSession();
        ses.beginTransaction();

        try {

            //Check if number exists
            String selectquery = "FROM PhoneNumber Where number = :number";
            Query<PhoneNumber> query = ses.createQuery(selectquery, PhoneNumber.class);
            query.setParameter("AFM", phonenumber);
            java.util.List<PhoneNumber> phonenumbers = query.list();

            if (!phonenumbers.isEmpty()) {  //If the phonenumber exists in DB, then Update
                System.out.println("Phonenumber already exists. Updationg...");
                String updatequerystring = "UPDATE PhoneNumber p SET p.program_id = :program_id WHERE p.number = :number";
                Query<PhoneNumber> updatequery = ses.createQuery(updatequerystring, PhoneNumber.class);
                updatequery.setParameter("program_id", program_id);
                updatequery.setParameter("number", phonenumber);
                System.out.println("Phonenumber Program Updated!");
            }
            else { //Else, create PhoneNumber Object 
                
                System.out.println("Phonenumber does not exists in table. Creating new object...");

                //Get program with specified id
                String getprogramstring = "From Program Where id = :id";
                Query<Program> programquery = ses.createQuery(getprogramstring, Program.class);
                programquery.setParameter("id", program_id);
                java.util.List<Program> programs = programquery.list();

                //Create phonenumber object
                PhoneNumber newphonenumber = new PhoneNumber(phonenumber, programs.get(0));

                //Store object in table
                ses.persist(newphonenumber);
                
                System.out.println("Saved Phonenumber with program");
            }

            ses.getTransaction().commit();
            ses.close();


        }
        catch (HibernateException e){
            System.out.println(e);
            return false;   //Unsuccessful
        }

        return true;    //Successful
    }

}

