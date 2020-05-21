package com.javademo.service.impl;


import com.javademo.service.RabbitMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author shuyi
 * @date 2020/5/19
 */
@Service
@Slf4j
public class RabbitMqServiceImpl implements RabbitMqService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;


    @Override
    public void testRabbitMq() {
       createTool();
    }

    public void createTool(){
        Map<String,Object> toolMap=new HashMap<>();
        String clientId=String.valueOf(UUID.randomUUID());
        String exchangeName=clientId+".exchange";
        amqpAdmin.declareExchange(new DirectExchange(exchangeName));
        System.out.println("创建Exchange完成");
        toolMap.put("exchange",exchangeName);


        //创建Queue队列，持久化
        String queueName=clientId+".queue";
        amqpAdmin.declareQueue(new Queue(queueName,true));
        System.out.println("创建queue完成");
        toolMap.put("queue",queueName);
        //创建绑定规则
        amqpAdmin.declareBinding(new Binding(queueName,Binding.DestinationType.QUEUE,
                exchangeName,queueName,null));
        System.out.println("创建绑定规则完成");
        log.info("队列创建信息"+toolMap);
        //消息放入队列
        mqSentTest(toolMap);
        //消费消息
        rabbitGetMessage(queueName);
    }

    public void mqSentTest(Map map){
        String messageId =String.valueOf(UUID.randomUUID());
        String messageData="队列消息，队列消息，队列消息";
        String crateTime= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> messageMap=new HashMap<>();
        messageMap.put("messageId",messageId);
        messageMap.put("messageData",messageData);
        messageMap.put("crateTime",crateTime);
        rabbitTemplate.convertAndSend(map.get("exchange").toString(),map.get("queue").toString(),messageMap);
        System.out.println("队列中接收的信息是"+messageMap);
    }

    public void rabbitGetMessage(String queue){
        Object o=rabbitTemplate.receiveAndConvert(queue);
        System.out.println("接收的队列消息类型是{}"+o.getClass());
        System.out.println("接收的队列消息是{}"+o.toString());
    }

}
