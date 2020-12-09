package com.fastree.springboot.message.rabbitmq.controller;

import com.fastree.springboot.message.rabbitmq.domain.RabbitMQOrderEntity;
import com.fastree.springboot.message.rabbitmq.service.RabbitMQOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQOrderController {
    private final RabbitMQOrderService orderService;

    public RabbitMQOrderController(RabbitMQOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/rabbitmq/order/send/default")
    public String sendDefault(@RequestBody RabbitMQOrderEntity order) {
        orderService.sendDefaultOrderMessage(order);
        return "send default";
    }

    @PostMapping("/rabbitmq/order/send/direct")
    public String sendDirect(@RequestBody RabbitMQOrderEntity order) {
        orderService.sendDirectOrderMessage(order);
        return "send direct";
    }

    @PostMapping("/rabbitmq/order/send/fanout")
    public String sendFanout(@RequestBody RabbitMQOrderEntity order) {
        orderService.sendFanoutOrderMessage(order);
        return "send fanout";
    }

    @PostMapping("/rabbitmq/order/send/topic")
    public String sendTopic(@RequestBody RabbitMQOrderEntity order) {
        orderService.sendTopicOrderMessage(order);
        return "send topic";
    }

    @PostMapping("/rabbitmq/order/send/headers")
    public String sendHeaders(@RequestBody RabbitMQOrderEntity order) {
        orderService.sendHeadersOrderMessage(order);
        return "send headers";
    }

    @GetMapping("/rabbitmq/order/receive/active")
    public RabbitMQOrderEntity receiveActive() {
        return orderService.activeReceiveOrderMessage();
    }
}
