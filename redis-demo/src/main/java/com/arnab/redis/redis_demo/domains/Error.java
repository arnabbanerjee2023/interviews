package com.arnab.redis.redis_demo.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    private Integer code;
    private String message;
    private String stackTrace;
}
