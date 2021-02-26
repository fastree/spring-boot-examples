package com.fastree.springboot.message.rabbitmq.listener;

import com.fastree.springboot.message.activemq.domain.ActiveMQOrderEntity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQOrderListener {
    @RabbitListener(queues = {"order.direct.queue"}, containerFactory = "customSimpleRabbitListenerContainerFactory")
    public void receiveOrderMessage1(ActiveMQOrderEntity order) {
        System.out.println("order.direct.queue ===> " + order);
    }

    @RabbitListener(queues = {"order.fanout.queue"}, containerFactory = "customSimpleRabbitListenerContainerFactory")
    public void receiveOrderMessage2(ActiveMQOrderEntity order) {
        System.out.println("order.fanout.queue ===> " + order);
    }

    @RabbitListener(queues = {"order.topic.queue"}, containerFactory = "customSimpleRabbitListenerContainerFactory")
    public void receiveOrderMessage3(ActiveMQOrderEntity order) {
        System.out.println("order.topic.queue ===> " + order);
    }

    @RabbitListener(queues = {"order.headers.queue"}, containerFactory = "customSimpleRabbitListenerContainerFactory")
    public void receiveOrderMessage4(ActiveMQOrderEntity order) {
        System.out.println("order.headers.queue ===> " + order);
    }
}
