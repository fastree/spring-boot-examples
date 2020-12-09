package com.fastree.springboot.message.activemq.service;


import com.fastree.springboot.message.activemq.domain.ActiveMQOrderEntity;

public interface ActiveMQOrderService {

    void sendOrderMessage(ActiveMQOrderEntity order);

    ActiveMQOrderEntity receiveOrderMessage();

    void publishOrderMessage(ActiveMQOrderEntity order);

    ActiveMQOrderEntity subscribeOrderMessage();
}
