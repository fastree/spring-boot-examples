package com.fastree.springboot.message.activemq.controller;

import com.fastree.springboot.message.activemq.domain.ActiveMQOrderEntity;
import com.fastree.springboot.message.activemq.service.ActiveMQOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActiveMQOrderController {

    private final ActiveMQOrderService orderService;

    public ActiveMQOrderController(ActiveMQOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/activemq/order/send")
    public String send(@RequestBody ActiveMQOrderEntity order) {
        orderService.sendOrderMessage(order);
        return "send";
    }

    @GetMapping("/activemq/order/receive")
    public ActiveMQOrderEntity receive() {
        return orderService.receiveOrderMessage();
    }

    @PostMapping("/activemq/order/publish")
    public String publish(@RequestBody ActiveMQOrderEntity order) {
        orderService.publishOrderMessage(order);
        return "publish";
    }

    @GetMapping("/activemq/order/subscribe")
    public ActiveMQOrderEntity subscribe() {
        return orderService.subscribeOrderMessage();
    }
}
