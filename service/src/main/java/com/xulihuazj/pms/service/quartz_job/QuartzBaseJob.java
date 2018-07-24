package com.xulihuazj.pms.service.quartz_job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Quartz 接口
 */
public interface QuartzBaseJob extends Job {

    @Override
    void execute(JobExecutionContext context) throws JobExecutionException;


}
