package com.xulihuazj.pms.context;


import com.xulihuazj.pms.enums.user.Source;
import com.xulihuazj.pms.validate.Remark;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description
 * @author xulihua
 * @Date 2017年8月3日下午4:15:34
 */
public class GlobalLocalContext{

    @Getter
    @Setter
    @Remark("用户ID")
    private Long userId;

    @Getter
    @Setter
    @Remark("来源")
    private Source source;

    @Getter
    @Setter
    @Remark("设备ID")
    private String deviceId;

    @Getter
    @Setter
    @Remark("版本信息")
    private String version;

    @Getter
    @Setter
    @Remark("用于接收来源信息")
    private String dtMonitor;

    @Getter
    @Setter
    @Remark("当前IP")
    private String currentIp;

    @Getter
    @Setter
    @Remark("幂等校验ID（从请求头中取出）")
    private String requestId;


}
