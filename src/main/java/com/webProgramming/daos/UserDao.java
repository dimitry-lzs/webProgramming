package com.webProgramming.daos;

import java.security.NoSuchAlgorithmException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.webProgramming.models.Admin;
import com.webProgramming.models.Client;
import com.webProgramming.models.Seller;
import com.webProgramming.models.User;
import com.webProgramming.models.Util;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.src.Login;

public class UserDao {

    public Login login(String username, String password, UserType userType) {
        Session session = null;
        User user = null;
        Login sucessfulLogin = null;
        String storedPassword=null;
        byte[] salt=null;
        boolean isPasswordCorrect=false;
        try {
            session = Util.getSessionFactory().openSession();
            String hql = "";

            switch (userType) {
                case UserType.ADMIN:

                    hql = "FROM Admin WHERE username = :username";
                    Query<Admin> query = session.createQuery(hql, Admin.class);

                    query.setParameter("username", username);
                    user = query.getSingleResult();
                    storedPassword=user.getPassword();
                    salt=user.getSalt();
                    isPasswordCorrect=verifyPassword(password,salt,storedPassword);
                    break;

                case UserType.SELLER:
                    hql = "FROM Seller WHERE username = :username";
                    Query<Seller> querySeller = session.createQuery(hql, Seller.class);

                    querySeller.setParameter("username", username);
                    user = querySeller.getSingleResult();
                    storedPassword=user.getPassword();
                    salt=user.getSalt();
                    isPasswordCorrect=verifyPassword(password,salt,storedPassword);
                    break;

                case UserType.CLIENT:
                    hql = "FROM Client WHERE username = :username";
                    Query<Client> queryCustomer = session.createQuery(hql, Client.class);

                    queryCustomer.setParameter("username", username);
                    user = queryCustomer.getSingleResult();
                    storedPassword=user.getPassword();
                    salt=user.getSalt();
                    isPasswordCorrect=verifyPassword(password,salt,storedPassword);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        if (user != null && isPasswordCorrect) {
            sucessfulLogin = new Login();
            sucessfulLogin.setUsername(username);
            sucessfulLogin.setPassword(password);
            sucessfulLogin.setType(userType);
            sucessfulLogin.setId(user.getId());
            sucessfulLogin.setUser(user);
        }

        return sucessfulLogin;
    }

    public static boolean verifyPassword(String password, byte[] salt, String storedHashedPassword) throws NoSuchAlgorithmException {
        String hashedPassword = User.hashPassword(password, salt);
        return hashedPassword.equals(storedHashedPassword);
    }

    public User findById(String id, UserType userType) {
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


    public boolean saveUser(User user) throws Exception {
        Transaction transaction = null;
        Session session = null;
        boolean success = false;

        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(user);
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

    public void reloadUser(User user) {
        Session session = null;

        try {
            session = Util.getSessionFactory().openSession();
            session.refresh(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}