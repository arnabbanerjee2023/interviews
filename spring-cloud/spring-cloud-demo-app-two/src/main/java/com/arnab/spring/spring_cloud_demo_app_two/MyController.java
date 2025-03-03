package com.arnab.spring.spring_cloud_demo_app_two;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/base")
public class MyController {
    private static final Logger LOG = LoggerFactory.getLogger(MyController.class.getName());

    @GetMapping(value = "/two")
    @CircuitBreaker(name = "cb-two", fallbackMethod = "fallbackTwo")
    public String api() {
        LOG.info("ARNAB: Hello World from App TWO!!");
        return "Hello World from App TWO!!";
    }

    public String fallbackTwo(Throwable t) {
        LOG.info("ARNAB: Fallback TWO!!");
        return "Fallback TWO!!";
    }
}
