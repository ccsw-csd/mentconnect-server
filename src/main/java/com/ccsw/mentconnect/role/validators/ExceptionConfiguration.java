package com.ccsw.mentconnect.role.validators;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
public class ExceptionConfiguration extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleConverterErrors(MethodArgumentTypeMismatchException exception) {
        Throwable cause = exception.getCause() // First cause is a ConversionException
                .getCause();
        return ResponseEntity.badRequest().body(cause.getMessage());
    }

}