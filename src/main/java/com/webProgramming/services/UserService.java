package com.webProgramming.services;

import java.security.NoSuchAlgorithmException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.webProgramming.daos.UserDao;
import com.webProgramming.exceptions.ServiceException;
import com.webProgramming.models.Admin;
import com.webProgramming.models.Client;
import com.webProgramming.models.Seller;
import com.webProgramming.models.User;
import com.webProgramming.models.Util;
import com.webProgramming.models.enums.UserType;
import com.webProgramming.src.Login;

public class UserService {
    private final UserDao userDao;
    private final SessionFactory factory;

    public UserService(SessionFactory factory, UserDao userDao) {
        this.factory = factory;
        this.userDao = userDao;
    }

    public UserService() {
        this(Util.getSessionFactory(), new UserDao());
    }

    public Login login(String username, String password, UserType userType) {
        Session session = factory.openSession();

        try {
            User user = userDao.findByUsername(username, userType, session);

            if (user == null) {
                return null;
            }

            String storedPassword = user.getPassword();
            byte[] salt = user.getSalt();

            if (verifyPassword(password, salt, storedPassword)) {
                return new Login(user, userType);
            }
        } catch (Exception exception) {
            throw new ServiceException("Error logging in user: " + username, exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public void saveUser(User user) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        UserType userType = null;

        try {
            if (user instanceof Admin) {
                userType = UserType.ADMIN;
            } else if (user instanceof Seller) {
                userType = UserType.SELLER;
            } else if (user instanceof Client) {
                userType = UserType.CLIENT;
            } else {
                throw new IllegalArgumentException("Invalid user type");
            }

            boolean usernameAvailable = usernameAvailable(user.getUsername(), userType);
            if (!usernameAvailable) {
                throw new ServiceException("Username " + user.getUsername() + " is already taken");
            }
            this.userDao.saveUser(user, session);
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

    public void reloadUser(User user) {
        Session session = factory.openSession();
        try {
            this.userDao.reloadUser(user, session);
        } catch (Exception exception) {
            throw new ServiceException("Service error while reloading user", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public User findById(String id, UserType userType) {
        Session session = factory.openSession();
        try {
            return this.userDao.findById(id, userType, session);
        } catch (Exception exception) {
            throw new ServiceException("Service error while finding user by id", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public User findByUsername(String username, UserType userType) {
        Session session = factory.openSession();
        try {
            return this.userDao.findByUsername(username, userType, session);
        } catch (Exception exception) {
            throw new ServiceException("Service error while finding user by username", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private boolean verifyPassword(String password, byte[] salt, String storedHashedPassword)
            throws NoSuchAlgorithmException {
        try {
            String hashedPassword = User.hashPassword(password, salt);
            return hashedPassword.equals(storedHashedPassword);
        } catch (Exception exception) {
            throw new ServiceException("Service error while verifying password", exception);
        }
    }

    public boolean usernameAvailable(String username, UserType userType) {
        boolean usernameAvailable = true;
        try {
            User user = this.findByUsername(username, userType);
            usernameAvailable = user == null;
        } catch (Exception exception) {
            usernameAvailable = false;
            throw new ServiceException("Service error while looking up for username: " + username, exception);
        }
        return usernameAvailable;
    }
}
