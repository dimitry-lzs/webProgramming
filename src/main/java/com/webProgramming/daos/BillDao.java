package com.webProgramming.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.webProgramming.models.Bill;
import com.webProgramming.models.Util;

public class BillDao {
    public boolean saveBill(Bill bill) throws Exception {
        Session session = null;
        Transaction transaction = null;
        boolean success = false;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(bill);
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
}