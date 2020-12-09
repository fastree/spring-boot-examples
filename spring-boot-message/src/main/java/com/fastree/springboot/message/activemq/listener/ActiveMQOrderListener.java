package com.fastree.springboot.message.activemq.listener;

import com.fastree.springboot.message.activemq.domain.ActiveMQOrderEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQOrderListener {

    @JmsListener(destination = "activemq.order.queue", containerFactory = "orderQueueListenerContainerFactory")
    public void receiveOrderMessage(ActiveMQOrderEntity order) {
        System.out.println("queue ==> " + order);
    }

    @JmsListener(destination = "activemq.order.topic", containerFactory = "orderTopicListenerContainerFactory")
    public void describeOrderMessage1(ActiveMQOrderEntity order) {
        System.out.println("topic1 ==> " + order);
    }

    @JmsListener(destination = "activemq.order.topic", containerFactory = "orderTopicListenerContainerFactory")
    public void describeOrderMessage2(ActiveMQOrderEntity order) {
        System.out.println("topic2 ==> " + order);
    }
}
