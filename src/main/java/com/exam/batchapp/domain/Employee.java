package com.exam.batchapp.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
    private Long id;
    private String name;
    private Double salary;

    // Getters and setters
}