package com.jwtauth.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoSuchMethodException.class)
    public ResponseEntity<Object> handleNoSuchMethodException(NoSuchMethodException exception) {
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
