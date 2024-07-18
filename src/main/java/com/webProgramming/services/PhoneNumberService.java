package com.webProgramming.services;

import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.webProgramming.daos.PhoneNumberDao;
import com.webProgramming.exceptions.ServiceException;
import com.webProgramming.models.PhoneNumber;
import com.webProgramming.models.Util;

public class PhoneNumberService {
    private final SessionFactory factory;
    private final PhoneNumberDao phoneNumberDao;

    public PhoneNumberService(SessionFactory factory, PhoneNumberDao phoneNumberDao) {
        this.factory = factory;
        this.phoneNumberDao = phoneNumberDao;
    }

    public PhoneNumberService() {
        this(Util.getSessionFactory(), new PhoneNumberDao());
    }

    public void savePhoneNumber(PhoneNumber phoneNumber) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            phoneNumberDao.savePhoneNumber(phoneNumber, session);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            throw new ServiceException("Service error while saving phone number", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updatePhoneNumber(PhoneNumber phoneNumber) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            phoneNumberDao.updatePhoneNumber(phoneNumber, session);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            throw new ServiceException("Service error while updating phone number", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public PhoneNumber findByNumber(String number) {
        Session session = factory.openSession();
        try {
            return phoneNumberDao.findByNumber(number, session);
        } catch (Exception exception) {
            throw new ServiceException("Service error while finding phone number by number", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static PhoneNumber generatePhoneNumber() {
        String number = "30";
        Random rand = new Random();

        for (int i = 0; i < 9; i++) {
            number += rand.nextInt(10);
        }

        return new PhoneNumber(number, null);
    }
}