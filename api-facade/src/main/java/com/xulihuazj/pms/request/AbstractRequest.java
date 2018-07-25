package com.xulihuazj.pms.request;

import com.xulihuazj.pms.entity.ToString;
import com.xulihuazj.pms.enums.user.Source;
import lombok.Getter;
import lombok.Setter;

public class AbstractRequest extends ToString {

    /**
     * 用户id
     */
    @Getter
    @Setter
    private Long userId;

    /**
     * 设备ID
     */
    @Getter
    @Setter
    private String deviceId;

    /**
     * 客户端版本号
     */
    @Getter
    @Setter
    private String version;

    /**
     * 授权令牌
     */
    @Getter
    @Setter
    private String token;

    /**
     * 请求来源（必传)
     */
    @Getter
    @Setter
    private Source source;

    /**
     * 用于接收来源信息
     */
    @Getter
    @Setter
    private String dtMonitor;

}
