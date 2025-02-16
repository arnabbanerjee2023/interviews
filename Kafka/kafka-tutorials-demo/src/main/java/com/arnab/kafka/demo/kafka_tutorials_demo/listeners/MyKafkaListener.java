package com.arnab.kafka.demo.kafka_tutorials_demo.listeners;

import com.arnab.kafka.demo.kafka_tutorials_demo.common.Constants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaListener {

    @KafkaListener(topics = Constants.TOPIC_1, groupId = Constants.KAFKA_LISTENER_GROUP_LOCAL_1)
    public void listener(String data) {
        System.out.println("Listener received: " + data + ".");
    }
}
