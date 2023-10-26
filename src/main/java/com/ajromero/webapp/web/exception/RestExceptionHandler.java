package com.ajromero.webapp.web.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(
            final RuntimeException ex,
            final WebRequest request) {
        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,ex.getMessage());
        return handleExceptionInternal(ex, apiError,
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {
        final BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        Map<String, String> errors = getFieldErrors(fieldErrors);
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                ex.getBody().getDetail(),
                errors);

        return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    private Map<String, String> getFieldErrors(List<FieldError> fieldErrors) {
        Map<String,String> errors = new HashMap<>();
        for (final FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }

    @ExceptionHandler({ EmailResourceException.class })
    protected ResponseEntity<Object> handleEmailException(
            final RuntimeException ex,
            final WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("email",ex.getMessage());
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,"Invalid request content.", errors);
        return handleExceptionInternal(ex, apiError,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @ExceptionHandler({ MyBadRequestException.class })
    protected ResponseEntity<Object> handleBadRequestException(
            final RuntimeException ex,
            final WebRequest request) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,ex.getMessage());
        return handleExceptionInternal(ex, apiError,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }
}
