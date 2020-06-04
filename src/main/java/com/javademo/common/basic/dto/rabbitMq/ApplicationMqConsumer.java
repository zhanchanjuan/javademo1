package com.javademo.common.basic.dto.rabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author shuyi
 * @date 2020/6/3
 */
@Component
public class ApplicationMqConsumer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Value("${app-notice.queue-name}")
    private String[] queueNames;

    @Bean
    public String[] queueNames(){
        return queueNames;
    }
    @RabbitListener(queues = {"#{queueNames}"})
    @RabbitHandler
    public void consumerMsg(Message message, Channel channel) throws IOException {
        System.out.println(queueNames);
        System.err.println("--------------------------------------");
        System.err.println("消费端Payload: " + message.getPayload());
        Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        System.out.println("deliveryTag:"+deliveryTag);

        //手工ACK
        channel.basicAck(deliveryTag, false);
    }

//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = "notice_queue",
//                    durable="true"),
//            exchange = @Exchange(value = "notice_exchange",
//                    durable="true",
//                    type= "topic",
//                    ignoreDeclarationExceptions = "true"),
//            key = "userId.*")
//    )
//    @RabbitHandler
//    public void consumerMsg(Message message, Channel channel) throws IOException {
//        System.out.println(queueNames);
//        System.err.println("--------------------------------------");
//        System.err.println("消费端Payload: " + message.getPayload());
//        Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
//        System.out.println("deliveryTag:"+deliveryTag);
//
//        //手工ACK
//        channel.basicAck(deliveryTag, false);
//    }

    public void creatChannel(String clientId) throws Exception {
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
        String exchangeName=clientId+ "exchange";
        String queueName=clientId+"queue";
        String routingKey="clientId.*";
        //创建绑定规则
        channel.queueBind(queueName,exchangeName,routingKey);

        rabbitTemplate.receiveAndConvert(queueName);
    }
}
