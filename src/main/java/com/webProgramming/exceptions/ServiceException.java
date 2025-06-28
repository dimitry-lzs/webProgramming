package com.webProgramming.exceptions;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ServiceException(String errorMessage) {
        super(errorMessage);
    }

    public ServiceException(String errorMessage, Throwable cause) {
        super(errorMessage + " - " + cause.getMessage(), cause);
    }
}
