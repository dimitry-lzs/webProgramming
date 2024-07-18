package com.webProgramming.interfaces;

import java.util.List;

import org.hibernate.Session;

import com.webProgramming.exceptions.DataAccessException;
import com.webProgramming.models.Program;
import com.webProgramming.models.User;
import com.webProgramming.models.enums.UserType;

public interface ProgramDataAccess {
    void saveProgram(Program program, Session session) throws DataAccessException;
    void updateProgram(Program program, Session session) throws DataAccessException;
    Program findById(String id, Session session) throws DataAccessException;
    List<Program> programs(User user, UserType type, Session session) throws DataAccessException;
}