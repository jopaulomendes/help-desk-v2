package com.jopaulo.authserviceapi.controller.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import models.exceptions.StandardError;
import models.exceptions.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<StandardError> handleBadCredentialsException(
            final RuntimeException exception, final HttpServletRequest request
    ){
        return ResponseEntity.status(UNAUTHORIZED).body(
                StandardError.builder()
                        .timestamp(now())
                        .status(UNAUTHORIZED.value())
                        .error(UNAUTHORIZED.getReasonPhrase())
                        .message(exception.getMessage())
                        .path(request.getRequestURI())
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<StandardError> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException exception, final HttpServletRequest request
    ){
        var error = ValidationException.builder()
                .timestamp(now())
                .status(BAD_REQUEST.value())
                .error("Exceção de Validação")
                .message("Exceção ao validar os atributos")
                .path(request.getRequestURI())
                .errors(new ArrayList<>())
                .build();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()){
            error.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(error);
    }
}
