package com.webProgramming.models;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.webProgramming.models.enums.UserType;

public class UserDao {
    public boolean login(String username, String password, UserType userType) {
        Session session = null;
        boolean loginSuccess = false;

        try {
            session = Util.getSessionFactory().openSession();
            String hql = "";

            switch (userType) {
                case UserType.ADMIN:
                    hql = "FROM Admin WHERE username = :username AND password = :password";
                    Query<Admin> query = session.createQuery(hql, Admin.class);

                    query.setParameter("username", username);
                    query.setParameter("password", password);

                    List<Admin> result = query.list();

                    loginSuccess = !result.isEmpty();
                    break;

                case UserType.SELLER:
                    hql = "FROM Seller WHERE username = :username AND password = :password";
                    Query<Seller> querySeller = session.createQuery(hql, Seller.class);

                    querySeller.setParameter("username", username);
                    querySeller.setParameter("password", password);

                    List<Seller> resultSeller = querySeller.list();
                    loginSuccess = !resultSeller.isEmpty();
                    break;

                case UserType.CLIENT:
                    hql = "FROM Customer WHERE username = :username AND password = :password";
                    Query<Client> queryCustomer = session.createQuery(hql, Client.class);

                    queryCustomer.setParameter("username", username);
                    queryCustomer.setParameter("password", password);

                    List<Client> resultCustomer = queryCustomer.list();
                    loginSuccess = !resultCustomer.isEmpty();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return loginSuccess;
    }

    public boolean saveUser(User user) {
        Transaction transaction = null;
        Session session = null;
        boolean success = false;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return success;
    }
}