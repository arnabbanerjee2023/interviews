package com.arnab.springboot.learning.springboot_learning.controller;

import com.arnab.springboot.learning.springboot_learning.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/base")
public class MyController {

    @Autowired
    private MyService service;

    @GetMapping(value = "/get-response")
    public String getResponse() {
        return service.getResponse();
    }

}
