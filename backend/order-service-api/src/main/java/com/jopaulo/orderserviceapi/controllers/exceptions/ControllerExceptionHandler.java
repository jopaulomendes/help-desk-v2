package com.jopaulo.orderserviceapi.controllers.exceptions;

import models.exceptions.GenericFeingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(GenericFeingException.class)
    ResponseEntity<Map> handleGenericFeingException(final GenericFeingException e){
        return ResponseEntity.status(e.getStatus()).body(e.getError());
    }
}
