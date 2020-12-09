package com.fastree.springboot.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

public class RabbitProduceMessageTest {

    public static void main(String[] args) {
        Connection connection = RabbitConnectionFactoryUtils.openConnection();
        Channel channel = null;
        try {
            channel = connection.createChannel();
            channel.basicPublish("order.default.exchange", "order.default.routing.key", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitmq".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                RabbitConnectionFactoryUtils.closeChannel(channel);
                RabbitConnectionFactoryUtils.closeConnection(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
