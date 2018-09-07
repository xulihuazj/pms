package com.xulihuazj.pms.queue.impl;

import com.xulihuazj.pms.model.MQMessage;
import com.xulihuazj.pms.model.MQSendResult;
import com.xulihuazj.pms.queue.MQSendRepository;
import org.springframework.stereotype.Repository;

/**
 * 消息发送仓储层
 */
@Repository
public class MQSendRepositoryImpl implements MQSendRepository {

    @Override
    public MQSendResult sendMessage(MQMessage message) {
//        Message message = new Message();

        return null;
    }
}
