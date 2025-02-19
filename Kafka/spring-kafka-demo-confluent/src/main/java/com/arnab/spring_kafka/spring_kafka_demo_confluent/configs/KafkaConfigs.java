package com.arnab.spring_kafka.spring_kafka_demo_confluent.configs;

import com.arnab.spring_kafka.spring_kafka_demo_confluent.common.Constants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfigs {

    @Bean
    public NewTopic programmaticTopic01() {
        return TopicBuilder.name(Constants.SPRING_KAFKA_TOPIC_2)
                .partitions(10)
                .replicas(2)
                .build();
    }
}
