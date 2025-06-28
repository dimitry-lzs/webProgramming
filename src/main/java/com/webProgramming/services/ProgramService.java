package com.webProgramming.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.webProgramming.daos.ProgramDao;
import com.webProgramming.exceptions.ServiceException;
import com.webProgramming.interfaces.ProgramDataAccess;
import com.webProgramming.models.Program;
import com.webProgramming.models.User;
import com.webProgramming.models.Util;
import com.webProgramming.models.enums.UserType;

public class ProgramService {
    private final SessionFactory factory;
    private final ProgramDataAccess programDao;

    public ProgramService(SessionFactory factory, ProgramDataAccess programDao) {
        this.factory = factory;
        this.programDao = programDao;
    }

    public ProgramService() {
        this(Util.getSessionFactory(), new ProgramDao());
    }

    public void saveProgram(Program program) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            programDao.saveProgram(program, session);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            throw new ServiceException("Service error while saving program", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateProgram(Program program) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            programDao.updateProgram(program, session);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            throw new ServiceException("Service error while updating program", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Program findById(String id) {
        Session session = factory.openSession();
        try {
            return programDao.findById(id, session);
        } catch (Exception exception) {
            throw new ServiceException("Service error while finding program by id", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Program> programs(User user, UserType type) {
        Session session = factory.openSession();
        try {
            return programDao.programs(user, type, session);
        } catch (Exception exception) {
            throw new ServiceException("Service error while finding programs by user and type", exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
