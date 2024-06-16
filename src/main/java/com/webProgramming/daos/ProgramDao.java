package com.webProgramming.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.webProgramming.models.Program;
import com.webProgramming.models.Util;

public class ProgramDao {
    public boolean createProgram(Program program) {
        Session session = null;
        Transaction transaction = null;
        boolean success = false;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(program);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return success;
    }
}
