package com.fastree.springboot.websocket.controller;

import com.fastree.springboot.websocket.domain.Shout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;


@Slf4j
@Controller
public class MarcoController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /*
    @MessageMapping方法支持以下参数:
        @Payload：方法参数提取消息的有效负载，并将其反序列化为声明的目标类型。参可以JSR-303验证。请记住，注释不需要出现，因为默认情况下，参数使用此注释标注。
        @DestinationVariable：用于访问从消息目的地提取的模板变量值的@DestinationVariable方法参数，例如/hotels/{hotel}。
        @Header：方法参数提取特定消息头值，并应用一个转换器将该值转换为声明的目标类型。
        @Headers：方法参数允许分配给一个Map对象，以访问所有的消息头值。
        MessageHeaders：方法参数可以访问所有的消息头。
        MessageHeaderAccessor：方法参数可以访问所有的消息头，专用于STOMP。
        Message<T>：用于访问消息头和消息体，如果匹配声明的类型，则将消息体反序列化。
        Principal：方法参数表示认证的对象实体。

    返回值处理取决于处理场景:
        默认情况下，来自@MessageMapping方法的返回值通过匹配的MessageConverter序列化为有效负载，并作为消息发送到brokerChannel，从那里将其广播给订阅者。
        出站消息的目的地与入站消息的目的地相同，但前缀为/topic。
    * */

    // 控制器方法内的映射 ===> /app/marco
    // 默认返回的订阅路径 <=== /topic/marco
    // 订阅默认返回的路径 ===> /topic/marco
    @MessageMapping("/marco")
    public Shout handleShout(Shout incoming) {
        log.info("Received message: " + incoming.getMessage());
        Shout outgoing = new Shout();
        outgoing.setMessage("the response was from MessageMapping!");
        return outgoing;
    }

    // 默认返回的订阅路径 <=== /app/once
    // 订阅默认返回的路径 ===> /app/once
    @SubscribeMapping("/once")
    public Shout handleSubscription() {
        Shout outgoing = new Shout();
        outgoing.setMessage("the response was from SubscribeMapping!");
        return outgoing;
    }

    // 控制器方法内的映射 ===> /app/notice
    // 手动返回的订阅路径 <=== /topic/notice
    // 订阅手动返回的路径 ===> /topic/notice
    @MessageMapping("/notice")
    @SendTo("/topic/notice")
    public Shout handleNotice(Shout incoming) {
        log.info("Received message: " + incoming.getMessage());
        Shout outgoing = new Shout();
        outgoing.setMessage("the notice have been broadcast!");
        return outgoing;
    }

    // 控制器方法内的映射 ===> /app/chapter
    // 手动返回的订阅路径 <=== /topic/chapter
    // 订阅手动返回的路径 ===> /user/topic/chapter
    @MessageMapping("/chapter")
    @SendToUser("/topic/chapter")
    public Shout handleChapter(Shout incoming) {
        log.info("Received message: " + incoming.getMessage());
        Shout outgoing = new Shout();
        outgoing.setMessage("the chapter have been published");
        return outgoing;
    }

}
