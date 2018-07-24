package com.xulihuazj.pms.utils.job;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用于在缓存中存储的JOB状态实体
 */
public class JobState {

    // 集群中机器的ID
    @Getter
    @Setter
    private String serverId;

    //最后活动时间
    @Getter
    @Setter
    private Date lastActivityTime;

    // 当前JOB的状态
    @Getter
    @Setter
    private JobStatusEnum jobStatus;

}
