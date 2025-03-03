package com.arnab.spring.spring_cloud_demo_client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/base")
public class MyController {
    private static final Logger LOG = LoggerFactory.getLogger(MyController.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/client")
    @CircuitBreaker(name = "cb-client", fallbackMethod = "fallbackClient")
    public String hello() {
        String uri1Client = discoveryClient.getInstances("SPRING-CLOUD-DEMO-APP-ONE")
                .get(0)
                .getUri()
                .toString() + "/base/one";

        String uri2Client = discoveryClient.getInstances("SPRING-CLOUD-DEMO-APP-TWO")
                .get(0)
                .getUri()
                .toString() + "/base/two";

        String message = restTemplate.getForObject(uri1Client, String.class) + " " +
                restTemplate.getForObject(uri2Client, String.class);

        return message;
    }

    public String fallbackClient(Throwable t) {
        LOG.info("ARNAB: Fallback CLIENT!!");
        return "Fallback CLIENT!!";
    }
}

@Configuration
class MyConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
