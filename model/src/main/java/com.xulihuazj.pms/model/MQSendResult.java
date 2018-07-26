package com.xulihuazj.pms.model;

import lombok.Getter;
import lombok.Setter;

/**
 * MQ 发送结果
 */
public class MQSendResult {

    /*
     * 是否成功
     */
    @Getter
    @Setter
    private boolean isSuccess;

    /*
     * 已发送的消息ID
     */
    @Getter
    @Setter
    private String messageId;

    /*
     * 已发送消息的主题
     */
    @Getter
    @Setter
    private String topic;
}
