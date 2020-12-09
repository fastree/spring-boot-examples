package com.fastree.springboot.redis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/*
RedisMessageListenerContainer
    ==> RedisConnectionFactory connectionFactory
    ==> Map<? extends MessageListener, Collection<? extends Topic>> messageListeners
        ==> MessageListener messageListener
        ==> ChannelTopic channelTopic
 */
@Component
public class UserMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 渠道名称
        String topic = new String(pattern);
        // 消息体
        String body = new String(message.getBody());
        System.out.println(topic + " ===> " + body);
    }
}
