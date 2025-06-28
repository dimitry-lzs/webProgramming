package com.webProgramming.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.webProgramming.exceptions.DataAccessException;
import com.webProgramming.interfaces.ProgramDataAccess;
import com.webProgramming.models.Admin;
import com.webProgramming.models.Program;
import com.webProgramming.models.Seller;
import com.webProgramming.models.User;
import com.webProgramming.models.enums.UserType;

public class ProgramDao implements ProgramDataAccess {
    @Override
    public void saveProgram(Program program, Session session) {
        try {
            session.persist(program);
        } catch (Exception exception) {
            throw new DataAccessException("Error creating program", exception);
        }
    }

    @Override
    public void updateProgram(Program program, Session session) {
        try {
            session.merge(program); // Merge the updated program with the persistent state
        } catch (Exception exception) {
            throw new DataAccessException("Error updating program", exception);
        }
    }

    @Override
    public Program findById(String id, Session session) {
        Program program = null;
        try {
            program = session.get(Program.class, id);
        } catch (Exception exception) {
            throw new DataAccessException("Error getting program", exception);
        }

        return program;
    }

    @Override
    public List<Program> programs(User user, UserType type, Session session) {
        List<Program> programs = null;
        int adminId;

        try {
            switch (type) {
                case UserType.ADMIN:
                    Admin admin = (Admin) user;
                    session.refresh(admin);
                    adminId = admin.getId();
                    break;
                case UserType.SELLER:
                    Seller seller = (Seller) user;
                    session.refresh(seller);
                    adminId = seller.getAdmin().getId();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid user type");
            }

            String hql = "SELECT p FROM Program p WHERE p.admin.id = :adminId";
            Query<Program> query = session.createQuery(hql, Program.class);
            programs = query.setParameter("adminId", adminId).list();

        } catch (Exception exception) {
            throw new DataAccessException("Error getting programs", exception);
        }
        return programs;
    }
}
