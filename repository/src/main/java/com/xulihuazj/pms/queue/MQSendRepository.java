package com.xulihuazj.pms.queue;

import com.xulihuazj.pms.model.MQMessage;
import com.xulihuazj.pms.model.MQSendResult;

public interface MQSendRepository {

    /**
     * 发送消息
     *
     * @param message 待发送的消息体
     * @return
     */
    MQSendResult sendMessage(MQMessage message);

}
