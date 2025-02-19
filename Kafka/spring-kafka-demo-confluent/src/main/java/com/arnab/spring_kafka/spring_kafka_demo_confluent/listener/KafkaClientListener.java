package com.arnab.spring_kafka.spring_kafka_demo_confluent.listener;

import com.arnab.spring_kafka.spring_kafka_demo_confluent.common.Constants;
import com.arnab.spring_kafka.spring_kafka_demo_confluent.dto.SpringKafkaDemoDTO;
import com.arnab.spring_kafka.spring_kafka_demo_confluent.dto.repository.SpringKafkaDemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class KafkaClientListener {

    @Autowired
    private SpringKafkaDemoRepository repository;

    @KafkaListener(groupId = Constants.GROUP_ID, topics = Constants.SPRING_KAFKA_TOPIC_1)
    public void listener(String data) {
        System.out.println("Listened Data: " + data);
        String uuid = UUID.randomUUID().toString();
        repository.save(new SpringKafkaDemoDTO(uuid, String.valueOf(System.currentTimeMillis()), data));
    }

    /*@KafkaListener(groupId = Constants.GROUP_ID, topics = Constants.SPRING_KAFKA_TOPIC_2)
    public void listener2(String data) {
        System.out.println("Listened Data 2: " + data.toUpperCase());
    }*/
}
