package com.webProgramming.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.webProgramming.models.Program;
import com.webProgramming.models.Util;

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
}
