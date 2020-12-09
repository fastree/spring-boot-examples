package com.fastree.springboot.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/marcopolo").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
        内置的simple message broker处理来自客户机的订阅请求，
        将它们存储在内存中，并向具有匹配目的地的已连接客户机广播消息。
        代理支持类路径目的地，包括对ant样式的目的地模式的订阅。
        registry.enableSimpleBroker("/queue", "/topic");

        请参阅所选消息代理(如RabbitMQ、ActiveMQ等)的STOMP文档，
        安装代理并在启用STOMP支持的情况下运行它。
        然后您可以在Spring配置中启用STOMP代理中继(而不是简单代理)。
        */
        registry.enableStompBrokerRelay("/queue", "/topic")
                .setRelayHost("192.168.188.128")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest")
                .setVirtualHost("/");

        /*
        配置一个或多个前缀，以筛选目标为应用程序注释方法的目的地。
        例如，前缀为“/app”的目的地可能由带注释的方法处理，
        而其他目的地可能以message broker为目标(例如，“/topic”、“/queue”)。
        在处理消息时，将从目的地删除匹配的前缀，以便形成查找路径。
        这意味着注释不应该包含目标前缀。没有尾随斜杠的前缀将自动追加一个斜杠。
        */
        registry.setApplicationDestinationPrefixes("/app");
    }
}
