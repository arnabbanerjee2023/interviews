package com.arnab.spring_kafka.spring_kafka_demo_confluent.service;

import com.arnab.spring_kafka.spring_kafka_demo_confluent.common.Constants;
import com.arnab.spring_kafka.spring_kafka_demo_confluent.dto.SpringKafkaDemoDTO;
import com.arnab.spring_kafka.spring_kafka_demo_confluent.dto.repository.SpringKafkaDemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaClientService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publish(String message) {
        String uuid = UUID.randomUUID().toString();
        kafkaTemplate.send(Constants.SPRING_KAFKA_TOPIC_1, uuid, message);
        //kafkaTemplate.send(Constants.SPRING_KAFKA_TOPIC_2, UUID.randomUUID().toString(), message);
    }
}
