package com.xulihuazj.pms.boot.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 用于测试环境自动执行 Job 调用
 *
 */

@Component
public class TestJobInitializer {

    private final Logger logger = LogManager.getLogger(TestJobInitializer.class);

    private final String validEnvironment = "test";

    @Value("${job.execute.environment}")
    private String environment;

    //每天0点执行一次
    //@Scheduled(fixedDelay = 120 * 1000)
    //@Scheduled(cron = "0 0 0 * * ?")
    //一分钟执行一次
    //@Scheduled(cron = "0/10 * * * * ?")
    private void sysExchangeDenialOfApproval() {
//        if (StringUtils.isNotBlank(environment) && environment.equals(validEnvironment)) {
//            LogHelper.info(logger, "测试环境自身定时执行Job，换房审核过期，系统自动不通过");
//
//        }
    }

}
