package com.fastree.springboot.message.activemq.service.impl;

import com.fastree.springboot.message.activemq.domain.ActiveMQOrderEntity;
import com.fastree.springboot.message.activemq.service.ActiveMQOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

@Slf4j
@Service
public class ActiveMQOrderServiceImpl implements ActiveMQOrderService {
    private final JmsTemplate jmsTemplate;
    private final MessageConverter messageConverter;
    private final Destination orderQueue;
    private final Destination orderTopic;

    public ActiveMQOrderServiceImpl(JmsTemplate jmsTemplate, @Qualifier("activeMQMessageConverter") MessageConverter messageConverter,
                                    Destination orderQueue, Destination orderTopic) {
        this.jmsTemplate = jmsTemplate;
        this.messageConverter = messageConverter;
        this.orderQueue = orderQueue;
        this.orderTopic = orderTopic;
    }

    @Override
    public void sendOrderMessage(ActiveMQOrderEntity order) {
        jmsTemplate.convertAndSend(orderQueue, order, message -> {
            message.setStringProperty("X_ORDER_SOURCE", "WEB");
            return message;
        });
    }

    @Override
    public ActiveMQOrderEntity receiveOrderMessage() {
        ActiveMQOrderEntity order = null;
        Message message = jmsTemplate.receive(orderQueue);
        try {
            String type = message.getStringProperty("X_ORDER_SOURCE");
            System.out.println("activemq type ====================> " + type);
            order = (ActiveMQOrderEntity) messageConverter.fromMessage(message);
        } catch (JMSException e) {
            log.error(e.getMessage());
        }
        return order;
    }

    @Override
    public void publishOrderMessage(ActiveMQOrderEntity order) {
        jmsTemplate.send(orderTopic, session -> messageConverter.toMessage(order, session));
    }

    @Override
    public ActiveMQOrderEntity subscribeOrderMessage() {
        return (ActiveMQOrderEntity) jmsTemplate.receiveAndConvert(orderTopic);
    }

}
