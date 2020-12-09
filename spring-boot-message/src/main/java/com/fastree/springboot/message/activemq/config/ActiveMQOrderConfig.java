package com.fastree.springboot.message.activemq.config;

import com.fastree.springboot.message.activemq.domain.ActiveMQOrderEntity;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

/*
ActiveMQAutoConfiguration
    ==> ActiveMQXAConnectionFactoryConfiguration
        ==> ConnectionFactory(jmsConnectionFactory)
    ==> ActiveMQConnectionFactoryConfiguration
        ===> JmsPoolConnectionFactory

JmsAutoConfiguration
    ==> JmsAnnotationDrivenConfiguration
        ==> DestinationResolver
        ==> JtaTransactionManager
        ==> MessageConverter
        ==> DefaultJmsListenerContainerFactoryConfigurer
        ==> DefaultJmsListenerContainerFactory
    ==> JmsTemplateConfiguration
        ==> JmsTemplate
            ==> DestinationResolver
            ==> MessageConverter
    ==> MessagingTemplateConfiguration
        ==> JmsMessagingTemplate
            ==> JmsTemplateConfiguration
 */
@Configuration
public class ActiveMQOrderConfig {

    @Bean("activeMQMessageConverter")
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");
        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        // 通信双方可以根据order值去定义映射的类
        typeIdMappings.put("order", ActiveMQOrderEntity.class);
        messageConverter.setTypeIdMappings(typeIdMappings);
        return messageConverter;
    }

    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue("activemq.order.queue");
    }

    @Bean
    public Destination orderTopic() {
        return new ActiveMQTopic("activemq.order.topic");
    }

    @Bean
    public JmsListenerContainerFactory<?> orderQueueListenerContainerFactory(DefaultJmsListenerContainerFactoryConfigurer configurer,
                                                                             @Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(false);
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> orderTopicListenerContainerFactory(DefaultJmsListenerContainerFactoryConfigurer configurer,
                                                                             @Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }

}
