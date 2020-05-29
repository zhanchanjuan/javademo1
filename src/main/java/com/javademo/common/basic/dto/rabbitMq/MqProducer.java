package com.javademo.common.basic.dto.rabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * mq生产者 原生写法 +实现流程
 * @author shuyi
 * @date 2020/5/29
 */
public class MqProducer {
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
        //声明 交换机  不同类型的交换机不能同时声明，这里只是写一下不同交换机的实现方式和不同
        // direct直流交换机
        String exchangeName="direct_exchange";
        String routingKey="direct.test";
        //topic交换机
        String exchangeName1="topic_exchange";
        String routingKey1="user.save"; //与user.*和user.#都能匹配，消息会发送到与之匹配的routingKey规则上去
        String routingKey2="user.update";
        String routingKey3="user.save.update";//只与user.#的routingKey规则匹配。
        //fanout交换机
        String exchangeName2="fanout_exchange";
        String msg="队列信息队列信息队列信息--通过直流交换机发送";
        channel.basicPublish(exchangeName,routingKey,null,msg.getBytes());
        channel.close();
        connection.close();
    }
}
