package com.exam.batchapp.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private EmployeeStepConfig employeeStepConfig;

    @Bean
    public Job employeeJob() {
        return jobBuilderFactory.get("employeeJob")
                .start(employeeStepConfig.employeeStep())
                .build();
    }
}