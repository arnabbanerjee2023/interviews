package com.arnab.spring.spring_cloud_demo_client;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
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
    private CircuitBreakerRegistry circuitBreakerRegistry;

    /*@GetMapping(value = "/client")
    public String hello() throws Exception {
        return circuitBreakerRegistry.circuitBreaker("cb-client-app-one").executeCallable(this::appOne)
                + " " +
                circuitBreakerRegistry.circuitBreaker("cb-client-app-two").executeCallable(this::appTwo);
    }*/

    @GetMapping(value = "/client")
    public String hello() throws Exception {
        return this.appOne()
                + " " +
                this.appTwo();
    }

    @CircuitBreaker(name = "cbClientAppOne", fallbackMethod = "fallbackClientAppOne")
    private String appOne() {
        return restTemplate.getForObject("http://127.0.0.1:8081/base/one", String.class);
    }

    @CircuitBreaker(name = "cbClientAppTwo", fallbackMethod = "fallbackClientAppTwo")
    private String appTwo() {
        return restTemplate.getForObject("http://127.0.0.1:8082/base/two", String.class);
    }

    public String fallbackClientAppOne(Throwable t) {
        LOG.info("ARNAB: Fallback CLIENT App One!!!");
        return "Fallback CLIENT App One!!!";
    }

    public String fallbackClientAppTwo(Throwable t) {
        LOG.info("ARNAB: Fallback CLIENT App Two!!!");
        return "Fallback CLIENT App Two!!!";
    }
}

@Configuration
class MyConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*@Bean
    public CircuitBreakerRegistry circuitBreakerConfig() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(5)
                .minimumNumberOfCalls(5)
                .slidingWindowSize(5)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .recordException(e ->
                        HttpStatus.INTERNAL_SERVER_ERROR.equals(e.getMessage()))
                .build();

        return CircuitBreakerRegistry.of(circuitBreakerConfig);

        //CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("cb-client-app-one");
    }*/

}
