package com.arnab.spring_kafka.spring_kafka_demo_confluent.service;

import com.arnab.spring_kafka.spring_kafka_demo_confluent.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaClientService {

    @Autowired
    public KafkaTemplate<String, String> kafkaTemplate;

    public void publish(String message) {
        kafkaTemplate.send(Constants.SPRING_KAFKA_TOPIC_1, UUID.randomUUID().toString(), message);
    }
}
