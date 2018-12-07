package com.accenture.cardsproject.exceptions;

import com.accenture.cardsproject.exceptions.base.ApplicationException;

public class ValidationException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ValidationException(String message) {
        super(message);
    }

}
