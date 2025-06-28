package com.webProgramming.interfaces;


import org.hibernate.Session;

import com.webProgramming.exceptions.DataAccessException;
import com.webProgramming.models.User;
import com.webProgramming.models.enums.UserType;

public interface UserDataAccess {
    User findByUsername(String username, UserType userType, Session session) throws DataAccessException;
    User findById(String id, UserType userType, Session session) throws DataAccessException;
    void saveUser(User user, Session session) throws DataAccessException;
    void reloadUser(User user, Session session) throws DataAccessException;
}