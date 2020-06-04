package com.javademo.common.basic.dto.rabbitMq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;

/**
 * @author shuyi
 * @date 2020/6/3
 */
@Component
public class ApplicationMqProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    public void send(Map<String,Object> messageMap) throws Exception {
        String userId =(String) messageMap.get("userId");
        Map<String,Object> chanelMap=creatChannel(userId);
        rabbitTemplate.convertAndSend(chanelMap.get("exchange").toString(),chanelMap.get("key").toString(),messageMap);
    }

    //不同应用传过来的消息，携带不同的routingKey,
    public Map<String,Object> creatChannel(String userId){
        Map<String,Object> map=new HashMap<>();
        String exchangeName="notice_exchange";
        amqpAdmin.declareExchange(new TopicExchange(exchangeName));
        map.put("exchange",exchangeName);
        String queueName="notice_queue";
        amqpAdmin.declareQueue(new Queue(queueName,true));
        map.put("queue",queueName);
        String routingKey="userId."+userId;
        map.put("key",routingKey);
        //创建绑定规则
       amqpAdmin.declareBinding(new Binding(queueName,Binding.DestinationType.QUEUE,exchangeName,routingKey,null));
       return map;
    }
}
