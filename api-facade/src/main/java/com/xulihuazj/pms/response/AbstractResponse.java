package com.xulihuazj.pms.response;

import com.xulihuazj.pms.entity.ToString;
import lombok.Getter;
import lombok.Setter;

/**
 * API 抽象 响应
 */
public class AbstractResponse extends ToString {

    /**
     * api返回码
     */
    @Getter
    @Setter
    private String statusCode;

    /**
     * api返回消息
     */
    @Getter
    @Setter
    private String message;

}
