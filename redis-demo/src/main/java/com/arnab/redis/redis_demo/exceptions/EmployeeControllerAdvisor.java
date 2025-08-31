package com.arnab.redis.redis_demo.exceptions;

import com.arnab.redis.redis_demo.domains.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeControllerAdvisor {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Error> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        Error error = new Error(HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex.getStackTrace().toString());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
