package com.javademo.common.util;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 消息的生产者
 * @author shuyi
 * @date 2020/5/19
 */
@Component
public class RabbitSender111 {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Autowired
    private  AmqpAdmin amqpAdmin;;

//    public static void sender(){
//        createTool();
//        String messageId =String.valueOf(UUID.randomUUID());
//        String messageData="放入队列的测试消息";
//        Map<String,Object> messageMap=new HashMap<>();
//        messageMap.put("messageId",messageId);
//        messageMap.put("messageData",messageData);
//        rabbitTemplate.convertAndSend("amqpadmin.exchange","myDemo.queue",messageMap);
//        System.out.println("队列中接收的信息是"+messageMap);
//    }


//    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
//        @Override
//        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//            System.err.println("correlationData: " + correlationData);
//            String messageId = correlationData.getId();
//            if(ack){
//                //如果confirm返回成功 则进行更新
//                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageId, Constants.ORDER_SEND_SUCCESS, new Date());
//            } else {
//                //失败则进行具体的后续操作:重试 或者补偿等手段
//                System.err.println("异常处理...");
//            }
//        }
//    };

//    public static void createTool(){
//        String clientId=String.valueOf(UUID.randomUUID());
//        String exchangeName=clientId+".exchange";
//        amqpAdmin.declareExchange(new DirectExchange(exchangeName));
//        System.out.println("创建Exchange完成");
//
//        //创建Queue队列，持久化
//        String queueName=clientId+".queue";
//        amqpAdmin.declareQueue(new Queue(queueName,true));
//        System.out.println("创建queue完成");
//        //创建绑定规则
//        amqpAdmin.declareBinding(new Binding(queueName,Binding.DestinationType.QUEUE,
//                exchangeName,queueName,null));
//        System.out.println("创建绑定规则完成");
//
//    }





}
