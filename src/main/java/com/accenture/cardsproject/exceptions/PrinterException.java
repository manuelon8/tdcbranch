package com.accenture.cardsproject.exceptions;

import com.accenture.cardsproject.exceptions.base.ApplicationException;

public class PrinterException extends ApplicationException {
    public PrinterException(String message) {
        super(message);
    }
}
