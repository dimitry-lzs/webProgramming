package com.webProgramming.daos;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.webProgramming.models.Call;
import com.webProgramming.models.Client;
import com.webProgramming.models.PhoneNumber;
import com.webProgramming.models.Util;


public class CallDao {

    public List<Call> calls(PhoneNumber numberCaller) throws Exception {
        List<Call> calls = null;

        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        try {

            String hql = "FROM Call WHERE caller.number = :numberCaller";
            Query<Call> query = session.createQuery(hql, Call.class);
            calls = query.setParameter("numberCaller", numberCaller.getNumber()).list();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }

        return calls;
    }


    public int totalCallDuration(PhoneNumber numberCaller) throws Exception {
        List<Call> calls = calls(numberCaller);
        int duration = 0;
        for (Call call : calls) {
            if (call.getBill() == null){
                duration += call.getDuration();
            }
        }
        calls.clear();
        return duration;
    }

    // This method is creating a call between a client and a random phone number
    public void createCall(Client client) throws Exception {
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            String number = "30";
            Random rand = new Random();

            for (int i = 0; i < 9; i++) {
                number += rand.nextInt(10);
            }

            PhoneNumber phoneNumber = new PhoneNumber(number, null);
            session.persist(phoneNumber);

            long startTime = ThreadLocalRandom.current().nextLong(1_599_999_000L, 1_600_000_000L); // Random start time between two Unix timestamps
            long endTime = ThreadLocalRandom.current().nextLong(startTime + 30, startTime + 7200); // Adjusted to ensure duration is between 30 seconds and 2 hours
            Call call = new Call(client.getPhoneNumber(), phoneNumber, startTime, endTime);

            session.persist(call);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
