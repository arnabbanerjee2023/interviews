package com.arnab.prototype_scope_demo.config;

import com.arnab.prototype_scope_demo.domain.MyPrototype;
import com.arnab.prototype_scope_demo.domain.MySingleton;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyConfigs {
    @Bean
    public MySingleton getMySingleton() {
        return new MySingleton(System.currentTimeMillis());
    }

    @Bean
    @Scope("prototype")
    public MyPrototype getMyPrototype() {
        return new MyPrototype(System.currentTimeMillis());
    }
}
