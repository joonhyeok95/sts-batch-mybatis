# sts-batch-mybatis
`MyBatisCursor`를 이용한 배치프로그램
+환경변수를 받아 쿼리에 데이터를 날릴 수 있어야 한다.
+Chunk방식으로 데이터를 효율적으로 업데이트 해야 한다.

# DB 세팅
Database Info : 10.4.6-MariaDB
Database Name : sts-test
Table Name : employee
Table Column
- id (int)
- name (varchar)
- salary (double)

# 테스트로그
CMD
```
>set ENV_REGION=US
>echo %ENV_REGION%
US

>build\libs>java -jar batchapp-0.0.1-SNAPSHOT.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.2)

2025-01-02 23:36:59.740  INFO 25068 --- [           main] com.exam.batchapp.BatchappApplication    : Starting BatchappApplication using Java 17.0.12 on DESKTOP-M0BI7JF with PID 25068 (C:\web\batchapp\build\libs\batchapp-0.0.1-SNAPSHOT.jar started by joonhyeok in C:\web\batchapp\build\libs)
2025-01-02 23:36:59.742  INFO 25068 --- [           main] com.exam.batchapp.BatchappApplication    : No active profile set, falling back to 1 default profile: "default"
2025-01-02 23:37:00.007  INFO 25068 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode
2025-01-02 23:37:00.008  INFO 25068 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JDBC repositories in DEFAULT mode.
2025-01-02 23:37:00.013  INFO 25068 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 3 ms. Found 0 JDBC repository interfaces.
2025-01-02 23:37:00.020  INFO 25068 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode
2025-01-02 23:37:00.020  INFO 25068 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2025-01-02 23:37:00.024  INFO 25068 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 0 ms. Found 0 JPA repository interfaces.
2025-01-02 23:37:00.055  WARN 25068 --- [           main] o.m.s.mapper.ClassPathMapperScanner      : No MyBatis mapper was found in '[com.exam.batchapp]' package. Please check your configuration.
2025-01-02 23:37:00.183  INFO 25068 --- [           main] com.zaxxer.hikari.HikariDataSource       : my - Starting...
2025-01-02 23:37:00.233  INFO 25068 --- [           main] com.zaxxer.hikari.HikariDataSource       : my - Start completed.
2025-01-02 23:37:00.318  INFO 25068 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2025-01-02 23:37:00.356  INFO 25068 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.6.10.Final
2025-01-02 23:37:00.484  INFO 25068 --- [           main] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
2025-01-02 23:37:00.581  INFO 25068 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.MariaDB103Dialect
2025-01-02 23:37:00.663  INFO 25068 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2025-01-02 23:37:00.668  INFO 25068 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2025-01-02 23:37:00.773  INFO 25068 --- [           main] c.exam.batchapp.jobtask.EmployeeWriter   : ::::: writer ...
2025-01-02 23:37:00.784  INFO 25068 --- [           main] c.exam.batchapp.jobtask.EmployeeReader   : ::::: 환경변수->US
2025-01-02 23:37:00.830  INFO 25068 --- [           main] o.s.b.c.r.s.JobRepositoryFactoryBean     : No database type set, using meta data indicating: MYSQL
2025-01-02 23:37:00.939  INFO 25068 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : No TaskExecutor has been set, defaulting to synchronous executor.
2025-01-02 23:37:01.040  INFO 25068 --- [           main] com.exam.batchapp.BatchappApplication    : Started BatchappApplication in 1.589 seconds (JVM running for 1.836)
2025-01-02 23:37:01.041  INFO 25068 --- [           main] o.s.b.a.b.JobLauncherApplicationRunner   : Running default command line with: []
2025-01-02 23:37:01.140  INFO 25068 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=employeeJob]] launched with the following parameters: [{}]
2025-01-02 23:37:01.163  INFO 25068 --- [           main] o.s.batch.core.job.SimpleStepHandler     : Executing step: [employeeStep]
2025-01-02 23:37:01.180  INFO 25068 --- [           main] c.e.batchapp.jobtask.EmployeeProcessor   : ::::: 로직중.....준혁, 급여 :28.53116706110002
2025-01-02 23:37:01.180  INFO 25068 --- [           main] c.e.batchapp.jobtask.EmployeeProcessor   : ::::: 로직중.....다솜, 급여 :59.91545082831005
2025-01-02 23:37:01.188  INFO 25068 --- [           main] o.s.batch.core.step.AbstractStep         : Step: [employeeStep] executed in 25ms
2025-01-02 23:37:01.192  INFO 25068 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=employeeJob]] completed with the following parameters: [{}] and the following status: [COMPLETED] in 40ms
2025-01-02 23:37:01.195  INFO 25068 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2025-01-02 23:37:01.197  INFO 25068 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : my - Shutdown initiated...
2025-01-02 23:37:01.199  INFO 25068 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : my - Shutdown completed.
```

# Spring Batch Table Create SQL
```
-- Batch Job Instance 테이블
CREATE TABLE BATCH_JOB_INSTANCE  (
    JOB_INSTANCE_ID BIGINT  NOT NULL PRIMARY KEY,
    VERSION BIGINT,
    JOB_NAME VARCHAR(100) NOT NULL,
    JOB_KEY VARCHAR(32) NOT NULL,
    CONSTRAINT JOB_INST_UN UNIQUE (JOB_NAME, JOB_KEY)
) ENGINE=InnoDB;

-- Batch Job Execution 테이블
CREATE TABLE BATCH_JOB_EXECUTION  (
    JOB_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY,
    VERSION BIGINT,
    JOB_INSTANCE_ID BIGINT NOT NULL,
    CREATE_TIME DATETIME(6) NOT NULL,
    START_TIME DATETIME(6) DEFAULT NULL,
    END_TIME DATETIME(6) DEFAULT NULL,
    STATUS VARCHAR(10),
    EXIT_CODE VARCHAR(2500),
    EXIT_MESSAGE VARCHAR(2500),
    LAST_UPDATED DATETIME(6),
    JOB_CONFIGURATION_LOCATION VARCHAR(2500) NULL,
    CONSTRAINT JOB_INST_EXEC_FK FOREIGN KEY (JOB_INSTANCE_ID)
    REFERENCES BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
) ENGINE=InnoDB;

-- Batch Job Execution Params 테이블
CREATE TABLE BATCH_JOB_EXECUTION_PARAMS  (
    JOB_EXECUTION_ID BIGINT NOT NULL,
    TYPE_CD VARCHAR(6) NOT NULL,
    KEY_NAME VARCHAR(100) NOT NULL,
    STRING_VAL VARCHAR(250),
    DATE_VAL DATETIME(6) DEFAULT NULL,
    LONG_VAL BIGINT,
    DOUBLE_VAL DOUBLE PRECISION,
    IDENTIFYING CHAR(1) NOT NULL,
    CONSTRAINT JOB_EXEC_PARAMS_FK FOREIGN KEY (JOB_EXECUTION_ID)
    REFERENCES BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

-- Batch Step Execution 테이블
CREATE TABLE BATCH_STEP_EXECUTION  (
    STEP_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY,
    VERSION BIGINT NOT NULL,
    STEP_NAME VARCHAR(100) NOT NULL,
    JOB_EXECUTION_ID BIGINT NOT NULL,
    START_TIME DATETIME(6) NOT NULL,
    END_TIME DATETIME(6) DEFAULT NULL,
    STATUS VARCHAR(10),
    COMMIT_COUNT BIGINT,
    READ_COUNT BIGINT,
    FILTER_COUNT BIGINT,
    WRITE_COUNT BIGINT,
    READ_SKIP_COUNT BIGINT,
    WRITE_SKIP_COUNT BIGINT,
    PROCESS_SKIP_COUNT BIGINT,
    ROLLBACK_COUNT BIGINT,
    EXIT_CODE VARCHAR(2500),
    EXIT_MESSAGE VARCHAR(2500),
    LAST_UPDATED DATETIME(6),
    CONSTRAINT JOB_EXEC_STEP_FK FOREIGN KEY (JOB_EXECUTION_ID)
    REFERENCES BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

-- Batch Step Execution Context 테이블
CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (
    STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
    SHORT_CONTEXT VARCHAR(2500) NOT NULL,
    SERIALIZED_CONTEXT TEXT,
    CONSTRAINT STEP_EXEC_CTX_FK FOREIGN KEY (STEP_EXECUTION_ID)
    REFERENCES BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
) ENGINE=InnoDB;

-- Batch Job Execution Context 테이블
CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (
    JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
    SHORT_CONTEXT VARCHAR(2500) NOT NULL,
    SERIALIZED_CONTEXT TEXT,
    CONSTRAINT JOB_EXEC_CTX_FK FOREIGN KEY (JOB_EXECUTION_ID)
    REFERENCES BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
) ENGINE=InnoDB;

-- Batch Job Sequence 테이블
CREATE TABLE BATCH_JOB_SEQ (
    ID BIGINT NOT NULL,
    UNIQUE_KEY CHAR(1) NOT NULL,
    CONSTRAINT UNIQUE_KEY_UN UNIQUE (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_SEQ (ID, UNIQUE_KEY) SELECT * FROM (SELECT 0 AS ID, '0' AS UNIQUE_KEY) AS tmp WHERE NOT EXISTS (SELECT * FROM BATCH_JOB_SEQ);

-- Batch Job Execution Sequence 테이블
CREATE TABLE BATCH_JOB_EXECUTION_SEQ (
    ID BIGINT NOT NULL,
    UNIQUE_KEY CHAR(1) NOT NULL,
    CONSTRAINT UNIQUE_KEY_UN UNIQUE (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_JOB_EXECUTION_SEQ (ID, UNIQUE_KEY) SELECT * FROM (SELECT 0 AS ID, '0' AS UNIQUE_KEY) AS tmp WHERE NOT EXISTS (SELECT * FROM BATCH_JOB_EXECUTION_SEQ);

-- Batch Step Execution Sequence 테이블
CREATE TABLE BATCH_STEP_EXECUTION_SEQ (
    ID BIGINT NOT NULL,
    UNIQUE_KEY CHAR(1) NOT NULL,
    CONSTRAINT UNIQUE_KEY_UN UNIQUE (UNIQUE_KEY)
) ENGINE=InnoDB;

INSERT INTO BATCH_STEP_EXECUTION_SEQ (ID, UNIQUE_KEY) SELECT * FROM (SELECT 0 AS ID, '0' AS UNIQUE_KEY) AS tmp WHERE NOT EXISTS (SELECT * FROM BATCH_STEP_EXECUTION_SEQ);
```
