package com.webProgramming.interfaces;

import org.hibernate.Session;

import com.webProgramming.exceptions.DataAccessException;
import com.webProgramming.models.PhoneNumber;

public interface PhoneNumberDataAccess {
    void savePhoneNumber(PhoneNumber phoneNumber, Session session) throws DataAccessException;
    void updatePhoneNumber(PhoneNumber phoneNumber, Session session) throws DataAccessException;
    PhoneNumber findByNumber(String number, Session session) throws DataAccessException;
}
