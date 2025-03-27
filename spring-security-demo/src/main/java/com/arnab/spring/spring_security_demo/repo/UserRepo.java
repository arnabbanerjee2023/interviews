package com.arnab.spring.spring_security_demo.repo;

import com.arnab.spring.spring_security_demo.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Query(value = "SELECT COALESCE(MAX(id), 0) FROM users", nativeQuery = true)
    Integer findHighestId();
}
