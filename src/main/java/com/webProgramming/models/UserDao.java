package com.webProgramming.models;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.webProgramming.models.enums.UserType;
import com.webProgramming.src.Login;

public class UserDao {

    public Login login(String username, String password, UserType userType) {
        Session session = null;
        User user = null;
        Login sucessfulLogin = null;

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
                    user = result.get(0);
                    break;

                case UserType.SELLER:
                    hql = "FROM Seller WHERE username = :username AND password = :password";
                    Query<Seller> querySeller = session.createQuery(hql, Seller.class);

                    querySeller.setParameter("username", username);
                    querySeller.setParameter("password", password);

                    List<Seller> resultSeller = querySeller.list();
                    user = resultSeller.get(0);
                    break;

                case UserType.CLIENT:
                    hql = "FROM Client WHERE username = :username AND password = :password";
                    Query<Client> queryCustomer = session.createQuery(hql, Client.class);

                    queryCustomer.setParameter("username", username);
                    queryCustomer.setParameter("password", password);

                    List<Client> resultCustomer = queryCustomer.list();
                    user = resultCustomer.get(0);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        if (user != null) {
            sucessfulLogin = new Login();
            sucessfulLogin.setUsername(username);
            sucessfulLogin.setPassword(password);
            sucessfulLogin.setType(userType);
            sucessfulLogin.setId(user.getId());
            sucessfulLogin.setUser(user);
        }

        return sucessfulLogin;
    }

    public User findById(int id, UserType userType) {
        Session session = null;
        User user = null;

        try {
            session = Util.getSessionFactory().openSession();
            String hql = "";

            switch (userType) {
                case UserType.ADMIN:
                    hql = "FROM Admin WHERE id = :id";
                    Query<Admin> query = session.createQuery(hql, Admin.class);

                    query.setParameter("id", id);

                    user = query.getSingleResult();
                    break;

                case UserType.SELLER:
                    hql = "FROM Seller WHERE id = :id";
                    Query<Seller> querySeller = session.createQuery(hql, Seller.class);

                    querySeller.setParameter("id", id);

                    user = querySeller.getSingleResult();
                    break;

                case UserType.CLIENT:
                    hql = "FROM Client WHERE id = :id";
                    Query<Client> queryCustomer = session.createQuery(hql, Client.class);

                    queryCustomer.setParameter("id", id);

                    user = queryCustomer.getSingleResult();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return user;
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