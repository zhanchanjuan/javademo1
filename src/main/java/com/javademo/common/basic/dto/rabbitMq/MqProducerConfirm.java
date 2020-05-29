package com.javademo.common.basic.dto.rabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * 消息确认 100%消息送达  消费者服务端用：MqConsumer.java
 * @author shuyi
 * @date 2020/5/29
 */
public class MqProducerConfirm {
    public static void main(String[] args) throws Exception {
        //mq连接工厂设置
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //建立连接
        Connection connection=connectionFactory.newConnection();
        //建立通道
        Channel channel=connection.createChannel();
        //消息确认  指定我们的消息投递模式
        channel.confirmSelect();
        //声明 交换机
        String exchangeName1="topic_exchange";
        String routingKey1="user.save";

        String msg="队列信息队列信息队列信息--通过主题交换机发送--测试confirm确认送达";
       channel.basicPublish(exchangeName1,routingKey1,null,msg.getBytes());
        //
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTay, boolean multiple) throws IOException {
                System.out.println(("-----no  ack------"));
            }

            @Override
            public void handleNack(long deliveryTay, boolean multiple) throws IOException {
                System.out.println("-------ack------");
            }
        });
        channel.close();
        connection.close();
    }

}
