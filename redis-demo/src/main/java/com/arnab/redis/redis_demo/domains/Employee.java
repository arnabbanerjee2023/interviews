package com.arnab.redis.redis_demo.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Employee {
    @Id
    private Long empId;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String location;
}
