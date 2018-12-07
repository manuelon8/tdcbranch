package com.accenture.cardsproject.exceptions;

import com.accenture.cardsproject.exceptions.base.ApplicationException;

public class InformationSendingException extends ApplicationException {
    public InformationSendingException(String message) {
        super(message);
    }
}
