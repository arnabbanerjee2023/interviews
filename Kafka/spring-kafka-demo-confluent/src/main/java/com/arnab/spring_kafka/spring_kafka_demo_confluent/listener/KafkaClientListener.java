package com.arnab.spring_kafka.spring_kafka_demo_confluent.listener;

import com.arnab.spring_kafka.spring_kafka_demo_confluent.common.Constants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaClientListener {

    @KafkaListener(groupId = Constants.GROUP_ID, topics = Constants.SPRING_KAFKA_TOPIC_1)
    public void listener(String data) {
        System.out.println("Listened Data: " + data);
    }
}
