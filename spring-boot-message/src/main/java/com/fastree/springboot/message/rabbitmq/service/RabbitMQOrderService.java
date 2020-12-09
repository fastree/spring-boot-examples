package com.fastree.springboot.message.rabbitmq.service;


import com.fastree.springboot.message.rabbitmq.domain.RabbitMQOrderEntity;

public interface RabbitMQOrderService {

    void sendDefaultOrderMessage(RabbitMQOrderEntity order);

    void sendDirectOrderMessage(RabbitMQOrderEntity order);

    void sendFanoutOrderMessage(RabbitMQOrderEntity order);

    void sendTopicOrderMessage(RabbitMQOrderEntity order);

    void sendHeadersOrderMessage(RabbitMQOrderEntity order);

    RabbitMQOrderEntity activeReceiveOrderMessage();
}
