package com.accenture.cardsproject.exceptions.base;

import com.accenture.cardsproject.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestREExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestREExceptionHandler.class);

    private BaseResponseError getError(String uri, Exception ex) {
        BaseResponseError error = new BaseResponseError();
        error.setMessage(ex.getMessage());
        error.setResource(uri);
        return error;
    }

    @ExceptionHandler(value = { ApplicationException.class })
    public ResponseEntity<BaseResponseError> handleBusinessError(Exception ex, WebRequest request) {
        log.error("Business rule", ex);
        BaseResponseError error = getError(request.getDescription(false), ex);
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    public ResponseEntity<BaseResponseError> handleResourceNotFound(Exception ex, WebRequest request) {
        log.error("Resource not found on system", ex);
        BaseResponseError error = getError(request.getDescription(false), ex);
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { DatabaseException.class })
    public ResponseEntity<BaseResponseError> handleDataIntegrityError(Exception ex, WebRequest request) {
        log.error("Database Error", ex);
        BaseResponseError error = getError(request.getDescription(false), ex);
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // Handler for validation errors by @Valid annotation
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Validation error", ex);
        StringBuilder message = new StringBuilder();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            message.append(error.getField() + ": " + error.getDefaultMessage() + " - ");
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            message.append(error.getObjectName() + ": " + error.getDefaultMessage() + " - ");
        }
        BaseResponseError error = new BaseResponseError();
        error.setMessage(message.toString());
        error.setResource(request.getDescription(false));
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Invalid parameter", ex);
        BaseResponseError error = new BaseResponseError();
        String errorMsg = ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : ex.getMessage();
        error.setMessage(errorMsg);
        error.setResource(request.getDescription(false));
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Unsupported content type", ex);
        BaseResponseError error = getError(request.getDescription(false), ex);
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
