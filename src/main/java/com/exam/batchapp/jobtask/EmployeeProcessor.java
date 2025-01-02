package com.exam.batchapp.jobtask;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.exam.batchapp.domain.Employee;
import com.exam.batchapp.domain.EmployeeOut;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProcessor implements ItemProcessor<Employee, EmployeeOut> {

    @Override
    public EmployeeOut process(Employee employee) throws Exception {
        // 필요한 처리 로직 구현
        log.info("::::: 로직중....." + employee.getName() + ", 급여 :" + employee.getSalary());
        EmployeeOut out = EmployeeOut.builder()
            .id(employee.getId())
            .name(employee.getName())
            .salary(employee.getSalary()*1.1)
            .build();
        return out;
    }
}