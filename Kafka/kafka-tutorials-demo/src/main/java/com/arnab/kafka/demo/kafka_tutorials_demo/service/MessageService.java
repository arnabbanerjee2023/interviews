package com.arnab.kafka.demo.kafka_tutorials_demo.service;

import com.arnab.kafka.demo.kafka_tutorials_demo.common.Constants;
import com.arnab.kafka.demo.kafka_tutorials_demo.models.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publish(MessageRequest request) {
        kafkaTemplate.send(Constants.TOPIC_1, request.message());
    }
}
