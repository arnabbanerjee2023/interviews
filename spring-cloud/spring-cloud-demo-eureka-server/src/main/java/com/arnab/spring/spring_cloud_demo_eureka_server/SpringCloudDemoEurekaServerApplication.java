package com.arnab.spring.spring_cloud_demo_eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudDemoEurekaServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDemoEurekaServerApplication.class, args);
	}
}
