package com.webProgramming.exceptions;

public class AuthenticationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public AuthenticationException(String errorMessage) {
        super(errorMessage);
    }

    public AuthenticationException(String errorMessage, Throwable cause) {
        super(errorMessage + " - " + cause.getMessage(), cause);
    }
}
