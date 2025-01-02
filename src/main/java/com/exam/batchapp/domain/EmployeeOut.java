package com.exam.batchapp.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmployeeOut {
    private Long id;
    private String name;
    private Double salary;

    // Getters and setters
}