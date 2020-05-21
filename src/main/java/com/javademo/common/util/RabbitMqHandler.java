package com.javademo.common.util;

import com.javademo.common.constants.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息队列处理器
 * @author shuyi
 * @date 2020/5/19
 */
@Component
@Slf4j
public class RabbitMqHandler {

   /* *//**
     * 日志打印处理handler
     * @param message 待处理的消息体
     *//*
    @RabbitListener(queues = RabbitMqConstants.QUEUE_LOG_PRINT)
    public void queueLogPrintHandler(String message){
        log.info("接收到操作日志信息：{}",message);
    }

    *//**
     * 主题模式处理handler
     * @param message
     *//*
    @RabbitListener(queues = RabbitMqConstants.TOPIC_ROUTING_KEY)
    public void queueTopicHandler(String message){
        log.info("主题模式处理器，接受信息:{}",message);
    }*/
}
