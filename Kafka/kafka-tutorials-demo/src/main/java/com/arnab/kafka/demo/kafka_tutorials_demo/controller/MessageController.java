package com.arnab.kafka.demo.kafka_tutorials_demo.controller;

import com.arnab.kafka.demo.kafka_tutorials_demo.common.Constants;
import com.arnab.kafka.demo.kafka_tutorials_demo.models.MessageRequest;
import com.arnab.kafka.demo.kafka_tutorials_demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constants.BASE_API)
public class MessageController {

    @Autowired
    private MessageService service;

    @PostMapping(value = Constants.MESSAGES_API_TO_PRODUCER)
    public void publish(@RequestBody MessageRequest request) {
        service.publish(request);
    }
}
