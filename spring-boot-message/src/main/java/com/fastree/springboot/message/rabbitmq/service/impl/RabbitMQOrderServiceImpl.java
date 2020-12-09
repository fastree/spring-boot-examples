package com.fastree.springboot.message.rabbitmq.service.impl;

import com.fastree.springboot.message.rabbitmq.domain.RabbitMQOrderEntity;
import com.fastree.springboot.message.rabbitmq.service.RabbitMQOrderService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

/*
order.direct.exchange	order.direct.routing.key	order.direct.queue

order.fanout.exchange	order.fanout.routing.key 	order.direct.queue
order.fanout.exchange	order.fanout.routing.key	order.fanout.queue
order.fanout.exchange	order.fanout.routing.key	order.topic.queue
order.fanout.exchange	order.fanout.routing.key	order.topic.queue

order.topic.exchange	order.topic.#		        order.topic.queue

order.headers.exchange	X_ORDER_SOURCE: WEB	        order.headers.queue

 */
@Service
public class RabbitMQOrderServiceImpl implements RabbitMQOrderService {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQOrderServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendDefaultOrderMessage(RabbitMQOrderEntity order) {
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                System.out.println("消息成功消费");
            } else {
                System.out.println("消息消费失败：" + cause);
            }
        });
        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("X_ORDER_SOURCE", "WEB");
        Message message = messageConverter.toMessage(order, messageProperties);
        rabbitTemplate.send(message);
    }

    @Override
    public RabbitMQOrderEntity activeReceiveOrderMessage() {
        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        Message message = rabbitTemplate.receive();
        if (message != null) {
            MessageProperties messageProperties = message.getMessageProperties();
            Object type = messageProperties.getHeader("X_ORDER_SOURCE");
            System.out.println("rabbitmq type ====================> " + type);
            return (RabbitMQOrderEntity) messageConverter.fromMessage(message);
        }
        return null;

//        return rabbitTemplate.receiveAndConvert(new ParameterizedTypeReference<RabbitMQOrderEntity>() {});
    }

    @Override
    public void sendDirectOrderMessage(RabbitMQOrderEntity order) {
        rabbitTemplate.convertAndSend("order.direct.exchange", "order.direct.routing.key", order);
    }

    @Override
    public void sendFanoutOrderMessage(RabbitMQOrderEntity order) {
        rabbitTemplate.convertAndSend("order.fanout.exchange", "don't care routing key", order);
    }

    @Override
    public void sendTopicOrderMessage(RabbitMQOrderEntity order) {
        rabbitTemplate.convertAndSend("order.topic.exchange", "order.topic.test", order);
    }

    @Override
    public void sendHeadersOrderMessage(RabbitMQOrderEntity order) {
        rabbitTemplate.convertAndSend("order.headers.exchange", "don't care routing key", order, message -> {
            MessageProperties properties = message.getMessageProperties();
            properties.setHeader("X_ORDER_SOURCE", "WEB");
            return message;
        });
    }
}
