package com.webProgramming.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.webProgramming.exceptions.DataAccessException;
import com.webProgramming.interfaces.BillDataAccess;
import com.webProgramming.models.Bill;
import com.webProgramming.models.Client;

public class BillDao implements BillDataAccess {
    @Override
    public void saveBill(Bill bill, Session session) {
        try {
            session.persist(bill);
        } catch (Exception exception) {
            throw new DataAccessException("Error saving bill", exception);
        }
    }

    @Override
    public Bill findByID(int billID, Session session) {
        Bill bill = null;
        try {
            String hql = "SELECT b FROM Bill b WHERE b.id = :billID";
            Query<Bill> query = session.createQuery(hql, Bill.class);
            bill = query.setParameter("billID", billID).uniqueResult();
        } catch (Exception exception) {
            throw new DataAccessException("Error finding bill by ID: " + billID, exception);
        }
        return bill;
    }

    @Override
    public List<Bill> getClientsBills(Client client, Session session) {
        List<Bill> bills = null;

        try {
            String hql = "SELECT b FROM Bill b WHERE b.client.id = :clientId";
            Query<Bill> query = session.createQuery(hql, Bill.class);
            bills = query.setParameter("clientId", client.getId()).list();
        } catch (Exception exception) {
            throw new DataAccessException("Error getting bills for client: " + client.getId(), exception);
        }
        return bills;
    }

    @Override
    public void updateBill(Bill bill, Session session) {
        try {
            session.merge(bill);
        } catch (Exception exception) {
            throw new DataAccessException("Error updating bill: " + bill.getID(), exception);
        }
    }
}