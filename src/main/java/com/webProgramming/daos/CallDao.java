package com.webProgramming.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.webProgramming.exceptions.DataAccessException;
import com.webProgramming.interfaces.CallDataAccess;
import com.webProgramming.models.Call;
import com.webProgramming.models.PhoneNumber;

public class CallDao implements CallDataAccess {

    @Override
    public List<Call> calls(PhoneNumber numberCaller, Session session) {
        List<Call> calls = null;
        try {
            String hql = "FROM Call WHERE caller.number = :numberCaller";
            Query<Call> query = session.createQuery(hql, Call.class);
            calls = query.setParameter("numberCaller", numberCaller.getNumber()).list();

        } catch (Exception exception) {
            throw new DataAccessException("Error finding calls by caller number: " + numberCaller.getNumber(), exception);
        }
        return calls;
    }

    @Override
    public void saveCall(Call call, Session session) {
        try {
            session.persist(call);
        } catch (Exception exception) {
            throw new DataAccessException("Error saving call: " + call.getCaller(), exception);
        }
    }
}
