package com.accenture.cardsproject.exceptions.base;

public class DatabaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DatabaseException(String message, Throwable ex) {
        super(message, ex);
    }

    public DatabaseException(String message) {
        super(message);
    }
}
