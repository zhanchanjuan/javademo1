package com.javademo.common.basic.dto.rabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信队列 DTL
 * @author shuyi
 * @date 2020/5/29
 */
public class MqDltQueueConsumer {
    public static void main(String[] args) throws Exception{
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

        String exchangeName="test_dlx_exchange";
        String exchangeType="topic";
        String queueName="test_dlx_queue";
        String routingKey="consumer.#";

        //通道里声明一个交换机
        channel.exchangeDeclare(exchangeName,exchangeType,true,false,null);
        Map<String,Object> agruments=new HashMap<String,Object>();
        agruments.put("x-dead-letter-exchange","dlx_exchange");
        //声明一个队列--将设置的agruments属性设置到声明队列上
        channel.queueDeclare(queueName,true,false,false,agruments);
        //建立绑定关系
        channel.queueBind(queueName,exchangeName,routingKey);
        //进行死信队列声明
        channel.exchangeDeclare("dlx.exchange", exchangeType, true, false, null);
        channel.queueDeclare("dlx.queue", true, false, false, null);
        channel.queueBind("dlx.queue", "dlx.exchange", "#");

        channel.basicConsume(queueName, true, new MyMqConsumer(channel));



    }
}
