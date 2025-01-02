package com.exam.batchapp.jobtask;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.exam.batchapp.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeReader {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    
    @Value("${envRegion}")
	private String env;

    public MyBatisCursorItemReader<Employee> reader() {
        log.info("::::: 환경변수->" + env);
        Map<String, Object> parameterValues = new HashMap<>();
        parameterValues.put("region", env);

        return new MyBatisCursorItemReaderBuilder<Employee>()
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("employee.selectAllEmployees")
                .parameterValues(parameterValues) // query param
                .build();
    }
}