package com.arnab.spring_kafka.spring_kafka_demo_confluent.dto.repository;

import com.arnab.spring_kafka.spring_kafka_demo_confluent.dto.SpringKafkaDemoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringKafkaDemoRepository extends JpaRepository<SpringKafkaDemoDTO, String> {
}
