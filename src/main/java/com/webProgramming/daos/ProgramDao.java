package com.webProgramming.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.webProgramming.models.Admin;
import com.webProgramming.models.Program;
import com.webProgramming.models.Seller;
import com.webProgramming.models.User;
import com.webProgramming.models.Util;
import com.webProgramming.models.enums.UserType;

public class ProgramDao {
    public boolean createProgram(Program program) throws Exception {
        Session session = null;
        Transaction transaction = null;
        boolean success = false;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(program);
            transaction.commit();
            success = true;
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
            throw exception;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return success;
    }
    
    public boolean updateProgram(Program program, Program newProgram) throws Exception {
        Session session = null;
        Transaction transaction = null;
        boolean success = false;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Update the managed entity's properties 
            program.setBenefits(newProgram.getBenefits());
            program.setCallTime(newProgram.getCallTime());
            program.setChargePerSecond(newProgram.getChargePerSecond());
            program.setFee(newProgram.getFee());
            program.setName(newProgram.getName());

            session.merge(program); // Merge the updated program with the persistent state
            transaction.commit(); // Commit the transaction
            success = true;
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
            throw exception;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return success;
    }

    public Program findById(String id) {
        Session session = null;
        Program program = null;

        try {
            session = Util.getSessionFactory().openSession();
            program = session.get(Program.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return program;
    }
    public List DataProgramList(User user, UserType type) throws Exception {
        List<Program> programs = null;
        int adminId;

        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        try {

            switch (type) {
                case UserType.ADMIN:
                    Admin admin = (Admin) user;
                    session.refresh(admin);
                    adminId = admin.getId();


                    break;
                case UserType.SELLER:
                    Seller seller = (Seller) user;
                    session.refresh(seller);
                    adminId = seller.getAdmin().getId();


                    break;
                default:
                    throw new AssertionError();
            }
            String hql = "SELECT p FROM Program p WHERE p.admin.id = :adminId";
            Query<Program> query = session.createQuery(hql, Program.class);
            programs = query.setParameter("adminId", adminId).list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close session.
            session.close();
        }

        return programs;
    }



}
