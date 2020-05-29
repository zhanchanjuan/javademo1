package com.javademo.common.basic.dto.rabbitMq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * 自定义消费者
 * @author shuyi
 * @date 2020/5/29
 */
public class MyMqConsumer extends DefaultConsumer {
    private Channel channel;
    public MyMqConsumer(Channel channel){
        super(channel);
        this.channel=channel;
    }
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.err.println("--------consume message-----------");
        System.err.println("consumeTag"+consumerTag);
        System.err.println("envelope"+envelope);
        System.err.println("properties"+properties);
        System.err.println("body"+new String(body));
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //模拟异常  如果请求头里是num=0,就一直重回队列，如果basicQos，prefetchCount设置的是1，那发送的其他消息就一直在交换机中排队，不能被消费
        if((Integer)properties.getHeaders().get("num")==0){
            //手动签收
            // channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true);
            //deliveryTag:该消息的index，multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息，requeue：被拒绝的是否重新入队列
            channel.basicNack(envelope.getDeliveryTag(),false,true);
        }else{
            //手动应答
            // 需要将autoAck设置为false，当消费者收到消息在合适的时候来显示的进行确认，说我已经接收到了该消息了，
            // RabbitMQ可以从队列中删除该消息了，可以通过显示调用channel.basicAck(envelope.getDeliveryTag(), false);来告诉消息服务器来删除消息
            channel.basicAck(envelope.getDeliveryTag(),false);
        }



    }
}
