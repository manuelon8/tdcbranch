package com.accenture.cardsproject.exceptions;

import com.accenture.cardsproject.exceptions.base.ApplicationException;

import java.io.Serializable;

public class ResourceException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ResourceException(String resourceName, String fieldName, Serializable fieldValue) {
        super("Error al operar con la entidad: " + resourceName + " - " + fieldName + ", value=" + fieldValue);
    }

    public ResourceException(String message) {
        super(message);
    }
}
