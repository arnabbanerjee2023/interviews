package com.arnab.spring_kafka.spring_kafka_demo_confluent.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "spring_kafka_demo")
public class SpringKafkaDemoDTO {
    @Id
    private String id;
    @Column(name = "timestamp_value")
    private String timestampValue;
    @Column(name = "value_str")
    private String valueStr;
}
