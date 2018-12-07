package com.accenture.cardsproject.exceptions;

import java.io.Serializable;

public class ResourceNotFoundException extends ResourceException{
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String resourceName, String fieldName, Serializable fieldValue) {
        super(String.format("%s no encontrado con %s: '%s'", resourceName, fieldName, fieldValue));
    }
}
