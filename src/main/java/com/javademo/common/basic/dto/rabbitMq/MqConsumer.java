package com.javademo.common.basic.dto.rabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;

/**
 * mq消费者 原生写法 +实现流程
 * @author shuyi
 * @date 2020/5/29
 */
public class MqConsumer {

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
        //建立连接
        Connection connection=connectionFactory.newConnection();
        //建立通道
        Channel channel=connection.createChannel();
        //声明 direct 直流交换机
        String exchangeName="direct_exchange";
        String exchangeType="direct";
        String queueName="direct_queue1";
        String routingKey="direct.test";
        //topic 主题交换机
        String exchangeName1="topic_exchange";
        String exchangeType1="topic";
        String queueName1="topic_queue1";
        String routingKey1="user.#";
        //fanout 输出交换机
        String exchangeName2="fanout_exchange";
        String exchangeType2="fanout";
        String queueName2="fanout_queue1";
        String routingKey2="";//不设路由键

        //通道里声明一个交换机
        channel.exchangeDeclare(exchangeName,exchangeType,true,false,false,null);
        //声明一个队列
        channel.queueDeclare(queueName,false,false,false,null);
        //建立绑定关系
        channel.queueBind(queueName,exchangeName,routingKey);

        //创建消费者消费消息




    }
}
