package com.webProgramming.interfaces;

import java.util.List;

import org.hibernate.Session;

import com.webProgramming.exceptions.DataAccessException;
import com.webProgramming.models.Call;
import com.webProgramming.models.PhoneNumber;


public interface CallDataAccess {
    List<Call> calls(PhoneNumber numberCaller, Session session) throws DataAccessException;
    void saveCall(Call call, Session session) throws DataAccessException;
}
