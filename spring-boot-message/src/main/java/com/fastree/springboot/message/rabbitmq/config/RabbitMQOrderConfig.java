package com.fastree.springboot.message.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.DirectRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
RabbitAutoConfiguration
    ==> RabbitAnnotationDrivenConfiguration
        ==> MessageConverter
        ==> MessageRecoverer
        ==> RabbitRetryTemplateCustomizer
        ==> SimpleRabbitListenerContainerFactoryConfigurer
        ==> SimpleRabbitListenerContainerFactory
        ==> DirectRabbitListenerContainerFactoryConfigurer
        ==> DirectRabbitListenerContainerFactory
    ==> RabbitConnectionFactoryCreator
        ==> CachingConnectionFactory
    ==> RabbitTemplateConfiguration
        ==> RabbitConnectionFactoryCreator
        ==> RabbitTemplate
        ==> AmqpAdmin

Direct：如果消息的RoutingKey与队列的BindingKey相同，那么消息会路由到该队列上。
Topic： 如果消息的RoutingKey与队列的BindingKey匹配（可能会包含通配符），那么消息将会路由到一个或多个这样的队列上。
Fanout：不管消息的RoutingKey和队列的BindingKey是什么，消息都将会路由到所有绑定的队列上。
Headers：与Topic类似，只不过要基于消息的头信息进行路由，而不是RoutingKey。
DeadLetter：捕获所有无法投递（也就是它们无法匹配所有已定义的Exchange和队列的binding关系）的消息。
 */
@Configuration
public class RabbitMQOrderConfig {
    private final AmqpAdmin amqpAdmin;

    public RabbitMQOrderConfig(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Exchange orderDefaultExchange() {
        return new DirectExchange("order.default.exchange", true, false, null);
    }

    @Bean
    public Queue orderDefaultQueue() {
        return new Queue("order.default.queue", true, false, false, null);
    }

    @Bean
    public Binding orderDefaultBinding() {
        return new Binding("order.default.queue", Binding.DestinationType.QUEUE, "order.default.exchange", "order.default.routing.key", null);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
                                                                                     ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public DirectRabbitListenerContainerFactory directRabbitListenerContainerFactory(DirectRabbitListenerContainerFactoryConfigurer configurer,
                                                                                     ConnectionFactory connectionFactory) {
        DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

}
