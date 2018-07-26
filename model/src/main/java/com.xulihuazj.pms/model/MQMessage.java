package com.xulihuazj.pms.model;

import lombok.Getter;
import lombok.Setter;

/**
 * MQ 消息体
 */
public class MQMessage {

    /*
     * 消息的ID
     */
    @Getter
    @Setter
    private String messageId;

    /*
     * 消息的主题
     */
    @Getter
    @Setter
    private String topic;

    /*
     * 消息的标签
     */
    @Getter
    @Setter
    private String tag;

    /*
     *
     */
    @Getter
    @Setter
    private byte[] body;

}
