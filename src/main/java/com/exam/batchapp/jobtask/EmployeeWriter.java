package com.exam.batchapp.jobtask;


import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exam.batchapp.domain.EmployeeOut;

@Slf4j
@Component
public class EmployeeWriter extends MyBatisBatchItemWriter<EmployeeOut> {

    @Autowired
    public EmployeeWriter(SqlSessionFactory sqlSessionFactory) {
        log.info("::::: writer ...");
        this.setSqlSessionFactory(sqlSessionFactory);
        this.setStatementId("employee.updateEmployee");
    }
}