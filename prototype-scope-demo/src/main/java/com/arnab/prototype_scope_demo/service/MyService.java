package com.arnab.prototype_scope_demo.service;

import com.arnab.prototype_scope_demo.domain.MyPrototype;
import com.arnab.prototype_scope_demo.domain.MySingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
interface MyPrototypeLookup {
    @Lookup
    MyPrototype getMyPrototype();
}

@Service
public class MyService {
    private final List<String> singletonApi = new ArrayList<>();
    private final List<String> prototypeApi = new ArrayList<>();

    @Autowired
    private MySingleton mySingleton;

    //@Autowired
    //private ApplicationContext context;

    @Autowired
    private MyPrototypeLookup myPrototypeLookup;

    public List<String> getSingletonApi() {
        singletonApi.add(String.valueOf(mySingleton.getTimestamp()));
        return singletonApi;
    }

    public List<String> getPrototypeApi() {
        //myPrototype = context.getBean(MyPrototype.class);
        prototypeApi.add(String.valueOf(myPrototypeLookup.getMyPrototype().getTimestamp()));
        return prototypeApi;
    }
}
