package com.webProgramming.services;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.webProgramming.daos.CallDao;
import com.webProgramming.daos.PhoneNumberDao;
import com.webProgramming.daos.UserDao;
import com.webProgramming.exceptions.ServiceException;
import com.webProgramming.interfaces.CallDataAccess;
import com.webProgramming.interfaces.PhoneNumberDataAccess;
import com.webProgramming.models.Call;
import com.webProgramming.models.Client;
import com.webProgramming.models.PhoneNumber;
import com.webProgramming.models.Util;

public class CallService {
    private final SessionFactory factory;

    private final CallDataAccess callDao;
    private final PhoneNumberDataAccess phoneNumberDao;
    private final UserDao userDao;

    public CallService(SessionFactory factory, CallDao callDao, PhoneNumberDao phoneNumberDao, UserDao userDao) {
        this.factory = factory;
        this.callDao = callDao;
        this.phoneNumberDao = phoneNumberDao;
        this.userDao = userDao;
    }

    public CallService() {
        this(Util.getSessionFactory(), new CallDao(), new PhoneNumberDao(), new UserDao());
    }

    public List<Call> calls(PhoneNumber callerNumber) {
        Session session = factory.openSession();
        try {
            return callDao.calls(callerNumber, session);
        } catch (Exception exception) {
            throw new ServiceException("Service error while getting calls", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void saveCall(Call call) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            callDao.saveCall(call, session);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            throw new ServiceException("Service error while saving call", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public int totalCallDuration(PhoneNumber numberCaller) {
        Session session = factory.openSession();
        try {
            List<Call> calls = callDao.calls(numberCaller, session);
            int duration = 0;

            for (Call call : calls) {
                if (call.getBill() == null){
                    duration += call.getDuration();
                }
            }

            calls.clear();
            return duration;

        } catch (Exception exception) {
            throw new ServiceException("Service error while getting total call duration", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void createClientWithCalls(Client client) {
        createClientWithCalls(client, 1); // Default callsCount is 1
    }

    public void createClientWithCalls(Client cleint, int callsCount) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        try {

            userDao.saveUser(cleint, session);

            for (int i = 0; i < callsCount; i++) {
                PhoneNumber receiverNumber = PhoneNumberService.generatePhoneNumber();

                long startTime = ThreadLocalRandom.current().nextLong(1_599_999_000L, 1_600_000_000L); // Random start time between two Unix timestamps
                long endTime = ThreadLocalRandom.current().nextLong(startTime + 30, startTime + 7200); // Adjusted to ensure duration is between 30 seconds and 2 hours
                Call call = new Call(cleint.getPhoneNumber(), receiverNumber, startTime, endTime);

                phoneNumberDao.savePhoneNumber(receiverNumber, session);
                callDao.saveCall(call, session);
            }

            transaction.commit();
        } catch (Exception exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
