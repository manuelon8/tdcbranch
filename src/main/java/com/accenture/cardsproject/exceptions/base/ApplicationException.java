package com.accenture.cardsproject.exceptions.base;

public class ApplicationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ApplicationException(String message, Throwable ex) {
        super(message, ex);
    }

    public ApplicationException(String message) {
        super(message);
    }
}
