package com.accenture.cardsproject.exceptions;

import com.accenture.cardsproject.exceptions.base.ApplicationException;

public class UpdatingBalanceException extends ApplicationException {
    public UpdatingBalanceException(String message) {
        super(message);
    }
}
