package com.arnab.prototype_scope_demo.controller;

import com.arnab.prototype_scope_demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class MyController {

    @Autowired
    private MyService service;

    @GetMapping(value = "/singleton-api")
    public List<String> getSingletonApi() {
        return service.getSingletonApi();
    }

    @GetMapping(value = "/prototype-api")
    public List<String> getPrototypeApi() {
        return service.getPrototypeApi();
    }
}
