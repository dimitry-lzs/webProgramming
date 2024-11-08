package com.webProgramming.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.webProgramming.models.PhoneNumber;
import com.webProgramming.models.Util;

public class PhoneNumberDao  {
    public boolean updatePhoneNumber(PhoneNumber phoneNumber) throws Exception {
        boolean success = false;
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(phoneNumber);
            transaction.commit();
            success = true;
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
            throw exception;
        }
        return success;
    }

    public PhoneNumber findByNumber(String number) {
        Session session = null;
        PhoneNumber phoneNumber = null;

        try {
            session = Util.getSessionFactory().openSession();
            phoneNumber = session.get(PhoneNumber.class, number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return phoneNumber;
    }

    public boolean savePhoneNumber(PhoneNumber phoneNumber) throws Exception {
        boolean success = false;
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(phoneNumber);
            transaction.commit();
            success = true;
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
            throw exception;
        }
        return success;
    }
}
