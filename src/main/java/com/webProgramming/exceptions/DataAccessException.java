package com.webProgramming.exceptions;

public class DataAccessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataAccessException(String errorMessage) {
        super(errorMessage);
    }

    public DataAccessException(String errorMessage, Throwable cause) {
        super(errorMessage + " - " + cause.getMessage(), cause);
    }
}
