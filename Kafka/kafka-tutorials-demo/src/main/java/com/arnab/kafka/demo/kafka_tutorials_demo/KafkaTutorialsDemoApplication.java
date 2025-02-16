package com.arnab.kafka.demo.kafka_tutorials_demo;

import com.arnab.kafka.demo.kafka_tutorials_demo.common.Constants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaTutorialsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaTutorialsDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
		return args -> {
			kafkaTemplate.send(Constants.TOPIC_1, "hello-world");
		};
	}
}
