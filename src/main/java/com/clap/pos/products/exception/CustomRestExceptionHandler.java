package com.clap.pos.products.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;


@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Errors> resourceNotFoundException(CustomException ex, WebRequest request) {
        var errors = new Errors();
        errors.addError(new ErrorMessage(request.getDescription(false), ex.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.valueOf(ex.getStatusCode()));
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var errors = new Errors();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            var message = new ErrorMessage(request.getDescription(false),
                    error.getField() + ": " + error.getDefaultMessage());
            errors.addError(message);
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            var message = new ErrorMessage(request.getDescription(false),
                    error.getObjectName() + ": " + error.getDefaultMessage());
            errors.addError(message);
        }

        return handleExceptionInternal(
                ex, errors, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var errors = new Errors();
        errors.addError(new ErrorMessage(request.getDescription(false), ex.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.valueOf((HttpStatus.BAD_REQUEST.value())));
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException1(Exception ex, WebRequest request) {
        var errors = new Errors();
        errors.addError(new ErrorMessage(request.getDescription(false), ex.getMessage() + " Cause: " + ex.getCause().getCause()));
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
