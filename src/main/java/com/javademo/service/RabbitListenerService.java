package com.javademo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author shuyi
 * @date 2020/5/19
 */
@Service
public class RabbitListenerService {

    /**
     * 添加监听器，自动获取消息队列消息
     * @param o
     */
    @RabbitListener(queues = {"client.queue"})
    public void rabbitMqListenerService(Object o){
        System.out.println("接收的消息队列消息"+o.getClass());
        System.out.println("接收的队列数据："+o.toString());
    }
}
