package com.aeroparker.aeroParker.exception;

import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse errorHandler(IllegalArgumentException ex) {
        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        err.setErrorCode("400");
        err.setTime(new Date());
        return err;
    }

    @ExceptionHandler(value = {EntityExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse errorHandler(EntityExistsException ex) {
        ErrorResponse err = new ErrorResponse();
        err.setMessage(ex.getMessage());
        err.setErrorCode("403");
        err.setTime(new Date());
        return err;
    }
}
