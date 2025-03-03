package com.arnab.spring.spring_cloud_demo_app_one;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler({CallNotPermittedException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public void handleCallNotPermittedException() {
    }
}
