package com.webProgramming.interfaces;

import java.util.List;

import org.hibernate.Session;

import com.webProgramming.exceptions.DataAccessException;
import com.webProgramming.models.Bill;
import com.webProgramming.models.Client;

public interface BillDataAccess {
    void saveBill(Bill bill, Session session) throws DataAccessException;
    void updateBill(Bill bill, Session session) throws DataAccessException;
    Bill findByID(int bill, Session session) throws DataAccessException;
    List<Bill> getClientsBills(Client client, Session session) throws DataAccessException;
}
