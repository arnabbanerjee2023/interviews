package com.arnab.springboot.learning.springboot_learning.service;

import com.arnab.springboot.learning.springboot_learning.models.Employee;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    private static final Logger LOG = LoggerFactory.getLogger(MyService.class.getName());

    @Autowired
    @Qualifier("singletonEmployee")
    private Employee singletonEmployee;

    @Autowired
    @Qualifier("prototypeEmployee")
    private Employee prototypeEmployee;

    @PreDestroy
    public void preDestroyMethod() {
        LOG.info("PreDestroy called -> {}", this.getResponse());
    }

    @PostConstruct
    public void postConstructMethod() {
        LOG.info("PostConstruct called -> {}", this.getResponse());
    }

    public String getResponse() {
        return "EARTH 1001 <-> singletonEmployee: " + singletonEmployee.toString()
                + "\nEARTH 1001 <-> prototypeEmployee: " + prototypeEmployee.toString();
    }

}
