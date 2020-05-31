package com.javademo;


import com.javademo.common.util.RabbitSender;
import com.javademo.entity.system.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author shuyi
 * @date 2020/5/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RabbitMqTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;


    @Autowired
    private RabbitSender rabbitSender;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * 测试直接模式发送
     */
/*    @Test
    public void sendMessage(){
        String message="测试将消息放在队列";
        rabbitTemplate.convertAndSend(RabbitMqConstants.QUEUE_LOG_PRINT,message);
        log.info("消息发送成功:{}"+message);
    }*/

    /**
     * 测试主题模式发送
     */
/*    @Test
    public void sendMessageByTopic(){
        String message="测试将消息放在队列";
        rabbitTemplate.convertAndSend(RabbitMqConstants.TOPIC_MODE_QUEUE,"topic.queue",message);
        log.info("消息发送成功:{}"+message);
    }*/

    @Test
    public void createExchange(){
        String clientId=String.valueOf(UUID.randomUUID());
        String exchangeName=clientId+".exchange";
        amqpAdmin.declareExchange(new DirectExchange(exchangeName));
        System.out.println("创建Exchange完成");

        //创建Queue队列，持久化
        String queueName=clientId+".queue";
        amqpAdmin.declareQueue(new Queue(queueName,true));
        System.out.println("创建queue完成");
        //创建绑定规则
        amqpAdmin.declareBinding(new Binding(queueName,Binding.DestinationType.QUEUE,
                exchangeName,queueName,null));
        System.out.println("创建绑定规则完成");

    }

    @Test
    public void mqSentTest(){
        String messageId =String.valueOf(UUID.randomUUID());
        String messageData="test message,hello";
        String crateTime= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> messageMap=new HashMap<>();
        messageMap.put("messageId",messageId);
        messageMap.put("messageData",messageData);
        messageMap.put("crateTime",crateTime);
        rabbitTemplate.convertAndSend("amqpadmin.exchange","myDemo.queue",messageMap);
        System.out.println("队列中接收的信息是"+rabbitTemplate);
    }

    @Test
    public void rabbitGetMessage(){
        Object o=rabbitTemplate.receiveAndConvert("myDemo.queue");
        System.out.println("接收的队列消息是{}"+o.getClass());
        System.out.println("接收的队列消息数量是{}"+o.toString());
    }




}
