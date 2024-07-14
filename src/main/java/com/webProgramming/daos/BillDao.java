package com.webProgramming.daos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import com.webProgramming.models.Bill;
import com.webProgramming.models.Util;
import com.webProgramming.models.Client;

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
}