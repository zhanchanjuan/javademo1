package com.javademo.common.basic.dto.rabbitMq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * mq生产者
 * @author shuyi
 * @date 2020/5/29
 */
public class MyMqConsumerTestProducer {
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
        String routingKey="consumer.test";

        for(int i=0;i<5;i++){
            Map<String,Object> headers=new HashMap<String, Object>();
            headers.put("num",i);
            AMQP.BasicProperties properties=new AMQP.BasicProperties.Builder()
                    .deliveryMode(2)
                    .contentEncoding("UTF-8")
                    .headers(headers)
                    .build();
            String msg="队列信息队列信息队列信息--测试自定义消费者的消息生产和消费1111";
            channel.basicPublish(exchangeName,routingKey,true,properties,msg.getBytes());
        }
    }
}
