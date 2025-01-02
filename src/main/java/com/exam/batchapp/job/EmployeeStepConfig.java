package com.exam.batchapp.job;


import com.exam.batchapp.domain.Employee;
import com.exam.batchapp.domain.EmployeeOut;
import com.exam.batchapp.jobtask.EmployeeProcessor;
import com.exam.batchapp.jobtask.EmployeeReader;
import com.exam.batchapp.jobtask.EmployeeWriter;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EmployeeReader employeeReader;

    @Autowired
    private EmployeeProcessor employeeProcessor;

    @Autowired
    private EmployeeWriter employeeWriter;

    @Bean
    public Step employeeStep() {
        return stepBuilderFactory.get("employeeStep")
                .<Employee, EmployeeOut>chunk(10)
                .reader(employeeReader.reader())
                .processor(employeeProcessor)
                .writer(employeeWriter)
                .allowStartIfComplete(true)  // 동일한jobId여도 재실행시키기
                .build();
    }
}