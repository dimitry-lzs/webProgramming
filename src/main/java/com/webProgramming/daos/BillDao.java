package com.webProgramming.daos;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.webProgramming.models.Bill;
import com.webProgramming.models.Client;
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

    public Bill findByID(String billID) {
        Bill bill = null;
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        try{
            String hql = "SELECT b FROM Bill b WHERE b.id = :billID";
            Query<Bill> query = session.createQuery(hql, Bill.class);
            bill = query.setParameter("billID", billID).uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return bill;
    }

    public List<Bill> viewClientsBills(Client client) {
        List<Bill> bills = null;
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        try{
            String hql = "SELECT b FROM Bill b WHERE b.client.id = :clientId";
            Query<Bill> query = session.createQuery(hql, Bill.class);
            bills = query.setParameter("clientId", client.getId()).list();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return bills;
    }

    public boolean payBill(Bill bill) {
        boolean success = false;
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            bill.setPaid(true);
            session.merge(bill);
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