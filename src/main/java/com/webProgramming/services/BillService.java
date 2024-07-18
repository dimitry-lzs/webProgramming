package com.webProgramming.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.webProgramming.daos.BillDao;
import com.webProgramming.exceptions.ServiceException;
import com.webProgramming.models.Client;
import com.webProgramming.models.Util;
import com.webProgramming.models.Bill;


public class BillService {
    private final SessionFactory factory;
    private final BillDao billDao;

    public BillService(SessionFactory factory, BillDao billDao) {
        this.factory = factory;
        this.billDao = billDao;
    }

    public BillService() {
        this(Util.getSessionFactory(), new BillDao());
    }

    public void saveBill(Bill bill) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            billDao.saveBill(bill, session);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            throw new ServiceException("Service error while saving bill", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateBill(Bill bill) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            billDao.updateBill(bill, session);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            throw new ServiceException("Service error while updating bill", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Bill findByID(int billId) {
        Session session = factory.openSession();
        try {
            return billDao.findByID(billId, session);
        } catch (Exception exception) {
            throw new ServiceException("Service error while finding bill by ID", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Bill> getClientsBills(Client client) {
        Session session = factory.openSession();
        try {
            return billDao.getClientsBills(client, session);
        } catch (Exception exception) {
            throw new ServiceException("Service error  while getting client's bills", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void payBill(int billId, int clientId) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Bill bill = billDao.findByID(billId, session);

            if (bill == null) {
                throw new ServiceException("Bill not found");
            }

            if (bill.getClient().getId() != clientId) {
                throw new ServiceException("Bill does not belong to client");
            }

            bill.setPaid(true);
            billDao.updateBill(bill, session);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            throw exception;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
