package com.fastree.springboot.message;


import com.rabbitmq.client.*;

import java.io.IOException;

public class RabbitConsumeMessageTest {

    public static void main(String[] args) throws IOException {

        Connection connection = com.fastree.springboot.message.RabbitConnectionFactoryUtils.openConnection();
        Channel channel = connection.createChannel();
        // 一次仅接受一条未经确认的消息
        channel.basicQos(1);

        try {
            channel.basicConsume("order.default.queue", false, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    try {
                        Thread.sleep(1000);
                        System.out.println(new String(body));
//                        // 模拟发生异常
//                        int i = 1 / 0;
                        // 手动确定接收
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    } catch (Exception e) {
//                        // 重新放入队列
//                        channel.basicNack(envelope.getDeliveryTag(), false, true);
//                        // 丢弃此条消息
//                        channel.basicNack(envelope.getDeliveryTag(), false, false);
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭用于持续监听消息
//                RabbitConnectionFactoryUtils.closeChannel(channel);
//                RabbitConnectionFactoryUtils.closeConnection(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
