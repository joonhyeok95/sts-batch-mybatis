-- 전체적인 BATCH TABLE JOIN
SELECT 
    bji.JOB_INSTANCE_ID,
    bji.JOB_NAME,
    bje.JOB_EXECUTION_ID,
    bje.START_TIME AS JOB_START_TIME,
    bje.END_TIME AS JOB_END_TIME,
    bje.STATUS AS JOB_STATUS,
    bje.EXIT_CODE AS JOB_EXIT_CODE,
    bje.EXIT_MESSAGE AS JOB_EXIT_MESSAGE,
    bje.LAST_UPDATED AS JOB_LAST_UPDATED,
    bjep.KEY_NAME AS PARAM_KEY,
    bjep.STRING_VAL AS PARAM_VALUE,
    bjec.SHORT_CONTEXT AS JOB_CONTEXT,
    bse.STEP_EXECUTION_ID,
    bse.STEP_NAME,
    bse.START_TIME AS STEP_START_TIME,
    bse.END_TIME AS STEP_END_TIME,
    bse.STATUS AS STEP_STATUS,
    bse.EXIT_CODE AS STEP_EXIT_CODE,
    bse.EXIT_MESSAGE AS STEP_EXIT_MESSAGE,
    bsec.SHORT_CONTEXT AS STEP_CONTEXT
FROM 
    BATCH_JOB_INSTANCE bji
JOIN 
    BATCH_JOB_EXECUTION bje ON bji.JOB_INSTANCE_ID = bje.JOB_INSTANCE_ID
LEFT JOIN 
    BATCH_JOB_EXECUTION_PARAMS bjep ON bje.JOB_EXECUTION_ID = bjep.JOB_EXECUTION_ID
LEFT JOIN 
    BATCH_JOB_EXECUTION_CONTEXT bjec ON bje.JOB_EXECUTION_ID = bjec.JOB_EXECUTION_ID
LEFT JOIN 
    BATCH_STEP_EXECUTION bse ON bje.JOB_EXECUTION_ID = bse.JOB_EXECUTION_ID
LEFT JOIN 
    BATCH_STEP_EXECUTION_CONTEXT bsec ON bse.STEP_EXECUTION_ID = bsec.STEP_EXECUTION_ID
ORDER BY 
    bji.JOB_INSTANCE_ID, bje.JOB_EXECUTION_ID, bse.STEP_EXECUTION_ID;
    
-- 특정 EXECUTION 조회
SELECT 
    bji.JOB_INSTANCE_ID,
    bji.JOB_NAME,
    bje.JOB_EXECUTION_ID,
    bje.START_TIME AS JOB_START_TIME,
    bje.END_TIME AS JOB_END_TIME,
    bje.STATUS AS JOB_STATUS,
    bje.EXIT_CODE AS JOB_EXIT_CODE,
    bje.EXIT_MESSAGE AS JOB_EXIT_MESSAGE,
    bje.LAST_UPDATED AS JOB_LAST_UPDATED,
    GROUP_CONCAT(CONCAT(bjep.KEY_NAME, '=', bjep.STRING_VAL) SEPARATOR '; ') AS JOB_PARAMETERS,
    bjec.SHORT_CONTEXT AS JOB_CONTEXT,
    bse.STEP_EXECUTION_ID,
    bse.STEP_NAME,
    bse.START_TIME AS STEP_START_TIME,
    bse.END_TIME AS STEP_END_TIME,
    bse.STATUS AS STEP_STATUS,
    bse.EXIT_CODE AS STEP_EXIT_CODE,
    bse.EXIT_MESSAGE AS STEP_EXIT_MESSAGE,
    bsec.SHORT_CONTEXT AS STEP_CONTEXT
FROM 
    BATCH_JOB_INSTANCE bji
JOIN 
    BATCH_JOB_EXECUTION bje ON bji.JOB_INSTANCE_ID = bje.JOB_INSTANCE_ID
LEFT JOIN 
    BATCH_JOB_EXECUTION_PARAMS bjep ON bje.JOB_EXECUTION_ID = bjep.JOB_EXECUTION_ID
LEFT JOIN 
    BATCH_JOB_EXECUTION_CONTEXT bjec ON bje.JOB_EXECUTION_ID = bjec.JOB_EXECUTION_ID
LEFT JOIN 
    BATCH_STEP_EXECUTION bse ON bje.JOB_EXECUTION_ID = bse.JOB_EXECUTION_ID
LEFT JOIN 
    BATCH_STEP_EXECUTION_CONTEXT bsec ON bse.STEP_EXECUTION_ID = bsec.STEP_EXECUTION_ID
WHERE 
    bje.JOB_EXECUTION_ID = 47
GROUP BY 
    bji.JOB_INSTANCE_ID, bje.JOB_EXECUTION_ID, bse.STEP_EXECUTION_ID
ORDER BY 
    bse.STEP_EXECUTION_ID;