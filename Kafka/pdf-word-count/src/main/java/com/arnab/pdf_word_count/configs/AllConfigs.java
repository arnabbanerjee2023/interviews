package com.arnab.pdf_word_count.configs;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AllConfigs {

   @Bean
    public Gson gson() {
       return new Gson();
   }
}
