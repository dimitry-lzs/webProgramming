package com.webProgramming;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.webProgramming.models.Program;
import com.webProgramming.models.Util;


@WebServlet("/viewPrograms")
public class ViewProgramsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter w = resp.getWriter();
        w.println("test get");

        //Get and open session.
        SessionFactory fac = Util.getSessionFactory();
        Session ses = fac.openSession();

        //Get response writer.
        PrintWriter writer = resp.getWriter();


        //Begin Transaction.
        writer.println("Beginning Transaction...");
        ses.beginTransaction();

        try {
            //Get Programs from DB.
            writer.println("Fetching Programs...");
            java.util.List<Program> programs = ses.createQuery("select p from Program p", Program.class).list();

            //Print every program's Name.
            writer.println("Printing Programs...");
            writer.println("------------------------");
            for (Program p : programs) {
               writer.println(p.getProgramName());
            }

        }
        catch(HibernateException e) {
            writer.println(e);
        }

        writer.println("------------------------");

        //Test to see if it got through here.
        writer.println("Done!");


        //Close stuff.
        ses.getTransaction().commit();
        writer.flush();
        writer.close();
    }

}
