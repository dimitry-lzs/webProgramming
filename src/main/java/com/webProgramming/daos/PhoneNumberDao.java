package com.webProgramming.daos;

import org.hibernate.Session;

import com.webProgramming.exceptions.DataAccessException;
import com.webProgramming.interfaces.PhoneNumberDataAccess;
import com.webProgramming.models.PhoneNumber;

public class PhoneNumberDao implements PhoneNumberDataAccess {

    @Override
    public void savePhoneNumber(PhoneNumber phoneNumber, Session session) {

        try {
            session.persist(phoneNumber);
        } catch (Exception exception) {
            throw new DataAccessException("Error saving phone number: " + phoneNumber.getNumber(), exception);
        }
    }

    @Override
    public void updatePhoneNumber(PhoneNumber phoneNumber, Session session) {
        try {
            session.merge(phoneNumber);
        } catch (Exception exception) {
            throw new DataAccessException("Error updating phone number: " + phoneNumber.getNumber(), exception);
        }
    }

    @Override
    public PhoneNumber findByNumber(String number, Session session) {
        PhoneNumber phoneNumber = null;

        try {
            phoneNumber = session.get(PhoneNumber.class, number);
        } catch (Exception exception) {
            throw new DataAccessException("Error finding phone number by number: " + number, exception);
        }
        return phoneNumber;
    }
}
