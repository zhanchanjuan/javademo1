package com.javademo.common.basic.dto.rabbitMq;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消息确认 100%消息送达 消费者服务端用：MqConsumer.java
 * @author shuyi
 * @date 2020/5/29
 */
public class MqProducerReturn {
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

        Connection connection=connectionFactory.newConnection();

        Channel channel=connection.createChannel();

        //声明 交换机
        String exchangeName1="topic_exchange";
        String routingKey1="user.save";
        String routingKeyError="abc.save";

        String msg="队列信息队列信息队列信息--通过主题交换机发送--测试confirm确认送达";
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.err.println("--------handle return-----------");
                System.err.println("replayCode"+replyCode);
                System.err.println("replyText"+replyText);
                System.err.println("exchange"+exchange);
                System.err.println("routingKey"+routingKey);
                System.err.println("properties: " + properties);
                System.err.println("body"+new String(body));
            }
        });
        //消息投递成功--被消费者消费
        channel.basicPublish(exchangeName1,routingKey1,true,null,msg.getBytes());
        //消息投递失败 ---触发ReturnListener
        channel.basicPublish(exchangeName1,routingKeyError,true,null,msg.getBytes());
    }

}
