package com.fastree.springboot.message;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MessagingApplicationTests {

    @Autowired
    AmqpAdmin amqpAdmin;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        amqpAdmin.declareExchange(new DirectExchange("order.direct.exchange", true, false, null));
        amqpAdmin.declareQueue(new Queue("order.direct.queue", true, false, false, null));
        amqpAdmin.declareBinding(new Binding("order.direct.queue", Binding.DestinationType.QUEUE, "order.direct.exchange", "order.direct.routing.key", null));
    }

}
