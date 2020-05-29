package com.javademo.common.basic.dto.rabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * mq消费者
 * @author shuyi
 * @date 2020/5/29
 */
public class MyMqConsumerTestComsumer {

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

        String exchangeName="test_consumer_exchange";
        String exchangeType="topic";
        String queueName="test_consumer_queue";
        String routingKey="consumer.#";

        //通道里声明一个交换机
        channel.exchangeDeclare(exchangeName,exchangeType,true,false,null);
        //声明一个队列
        channel.queueDeclare(queueName,true,false,false,null);
        //建立绑定关系
        channel.queueBind(queueName,exchangeName,routingKey);

        //消费端限流  （prefetchSize，prefetchCount，global）
        // prefetchCount 限制mq不要同时给一个消费者推送多于n个消息
        // global true/false 是否将上面设置应用于channel，简单点说，就是上面限制是channel级别的还是consumer级别
        channel.basicQos(0,5,false);

        //消费者签收消息basicConsume
        //channel.basicConsume(QUEUE_NAME, true, consumer);
        //autoAck：是否自动ack，如果不自动ack，需要使用channel.ack、channel.nack、channel.basicReject 进行消息应答
        //自动签收  创建消费者消费消息,b:true 自动ack
//        channel.basicConsume(queueName,true,new MyMqConsumer(channel));
        //手动签收  必须将autoAck=false;
        channel.basicConsume(queueName,false,new MyMqConsumer(channel));




    }
}
