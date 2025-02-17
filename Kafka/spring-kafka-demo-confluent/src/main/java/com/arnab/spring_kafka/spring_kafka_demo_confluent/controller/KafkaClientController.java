package com.arnab.spring_kafka.spring_kafka_demo_confluent.controller;

import com.arnab.spring_kafka.spring_kafka_demo_confluent.common.Constants;
import com.arnab.spring_kafka.spring_kafka_demo_confluent.service.KafkaClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constants.BASE_API_PATH)
public class KafkaClientController {

    @Autowired
    private KafkaClientService service;

    @PostMapping(value = "/publish", consumes = MediaType.TEXT_PLAIN_VALUE)
    public void publish(@RequestBody String message) {
        service.publish(message);
    }
}
