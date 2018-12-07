package com.accenture.cardsproject.exceptions.base;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BaseResponseError implements Serializable {

    private static final long serialVersionUID = 1L;

    private String resource;
    private String message;
    private String timestamp;

    public BaseResponseError( ) {
        timestamp = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(Calendar.getInstance().getTime());
    }

    public String getResource() {
        return resource;
    }
    public void setResource(String resource) {
        this.resource = resource;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getTimestamp() {
        return timestamp;
    }
}
