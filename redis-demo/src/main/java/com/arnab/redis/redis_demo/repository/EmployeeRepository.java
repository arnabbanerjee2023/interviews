package com.arnab.redis.redis_demo.repository;

import com.arnab.redis.redis_demo.domains.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, Long> {
}
