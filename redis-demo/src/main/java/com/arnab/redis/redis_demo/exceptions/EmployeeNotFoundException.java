package com.arnab.redis.redis_demo.exceptions;

public class EmployeeNotFoundException extends BaseException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
