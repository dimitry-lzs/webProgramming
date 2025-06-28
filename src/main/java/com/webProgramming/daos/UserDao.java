package com.webProgramming.daos;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.webProgramming.models.Admin;
import com.webProgramming.models.Client;
import com.webProgramming.models.Seller;
import com.webProgramming.models.User;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.exceptions.DataAccessException;
import com.webProgramming.interfaces.UserDataAccess;

public class UserDao implements UserDataAccess {

    @Override
    public User findByUsername(String username, UserType userType, Session session) {
        User user = null;

        try {
            String hql = getQueryByUserType(userType, "username");
            Class<? extends User> userClass = getUserClass(userType);
            Query<? extends User> query = session.createQuery(hql, userClass);

            query.setParameter("username", username);
            user = query.getSingleResultOrNull();
        } catch (Exception exception) {
            throw new DataAccessException("Error finding user by username: " + username, exception);
        }
        return user;
    }

    @Override
    public User findById(String id, UserType userType, Session session) {
        Class<? extends User> userClass = null;
        User user = null;

        try {
            String hql = getQueryByUserType(userType, "id");
            userClass = getUserClass(userType);

            Query<? extends User> query = session.createQuery(hql, userClass);

            query.setParameter("id", id);
            user = query.getSingleResultOrNull();
        } catch (Exception exception) {
            throw new DataAccessException("Error finding user by ID: " + id, exception);
        }
        return user;
    }

    @Override
    public void saveUser(User user, Session session) {
        try {
            session.persist(user);
        } catch (Exception exception) {
            throw new DataAccessException("Error saving user: " + user.getUsername(), exception);
        }
    }

    @Override
    public void reloadUser(User user, Session session) {
        try {
            session.refresh(user);
        } catch (Exception exception) {
            throw new DataAccessException("Error reloading User: " + user.getUsername(), exception);
        }
    }

    private final static String getQueryByUserType(UserType userType, String field) {
        switch (userType) {
            case ADMIN:
                return "FROM Admin WHERE " + field + " = :" + field;
            case SELLER:
                return "FROM Seller WHERE " + field + " = :" + field;
            case CLIENT:
                return "FROM Client WHERE " + field + " = :" + field;
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }

    private final static Class<? extends User> getUserClass(UserType userType) {
        switch (userType) {
            case ADMIN:
                return Admin.class;
            case SELLER:
                return Seller.class;
            case CLIENT:
                return Client.class;
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }
}