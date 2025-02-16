package com.arnab.springboot.learning.springboot_learning.config;

import com.arnab.springboot.learning.springboot_learning.models.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Random;

@Configuration
public class MyConfig {

    @Bean(name = "singletonEmployee")
    public Employee getEmployee() {
        return new Employee(new Random().nextLong(0L, 1000000L), "Arnab", "KOLKATA");
    }

    @Bean(name = "prototypeEmployee")
    @Scope("prototype")
    public Employee getEmployeePrototype() {
        return new Employee(new Random().nextLong(0L, 1000000L), "Arnab", "KOLKATA");
    }

}
