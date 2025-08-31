package com.arnab.redis.redis_demo.service;

import com.arnab.redis.redis_demo.domains.Employee;
import com.arnab.redis.redis_demo.exceptions.EmployeeNotFoundException;
import com.arnab.redis.redis_demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    public Employee createEmployee(Employee employee) {
        long count = employeeRepository.findAll().size();

        employee.setEmpId(count + 1);

        redisTemplate.opsForValue().set(String.valueOf(employee.getEmpId()), employeeRepository.save(employee));

        return (Employee) redisTemplate.opsForValue().get(String.valueOf(employee.getEmpId()));
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long empId) {
        Employee employee = (Employee) redisTemplate.opsForValue().get(String.valueOf(empId));
        if (Objects.isNull(employee)) {
            employee = employeeRepository.findById(empId)
                    .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + empId));
            redisTemplate.opsForValue().set(String.valueOf(empId), employee);
        }
        return employee;
    }

    public ResponseEntity<String> refreshCache() {
        // Clear existing cache
        redisTemplate.getConnectionFactory().getConnection().flushDb();
        this.getAllEmployees()
                .forEach(employee -> redisTemplate.opsForValue().set(String.valueOf(employee.getEmpId()), employee));
        return ResponseEntity.ok("Cache refreshed successfully.");
    }

    public ResponseEntity<Employee> updateEmployee(Long empId, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + empId));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setLocation(employeeDetails.getLocation());
        Employee updatedEmployee = employeeRepository.save(employee);
        redisTemplate.opsForValue().set(String.valueOf(empId), updatedEmployee);
        return ResponseEntity.ok(updatedEmployee);
    }
}
