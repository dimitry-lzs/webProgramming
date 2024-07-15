package com.webProgramming.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.webProgramming.models.Call;
import com.webProgramming.models.PhoneNumber;
import com.webProgramming.models.Util;


public class CallDao {


    public List<Call> DataCallList(PhoneNumber numberCaller) throws Exception {
        List<Call> calls = null;

        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        try {

            String hql = "FROM Call WHERE caller.number = :numberCaller";
            Query<Call> query = session.createQuery(hql, Call.class);
            calls = query.setParameter("numberCaller", numberCaller.getNumber()).list();
  
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return calls;
    }  


    public int TotalCallDuration(PhoneNumber numberCaller) throws Exception {
        List<Call> calls = DataCallList(numberCaller);
        int TotalCallDuration = 0;
        for (Call call : calls) {
            if (call.getBill() == null){
                TotalCallDuration += call.getDuration();
            }
        }
        calls.clear();
        return TotalCallDuration;
    }

}
