package com.javademo.common.component;




import com.javademo.entity.system.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 相当于订单微服务具体处理MQ服务器中的消息的业务类
 * Created by 书一 on 2020/5/31.
 */
@Component
public class RabbitReceiver {

    //订单支付成功  ==短信消息消费
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-1",
                    durable="true"),
            exchange = @Exchange(value = "exchange-1",
                    durable="true",
                    type= "topic",
                    ignoreDeclarationExceptions = "true"),
            key = "springboot.*"
    )
    )
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        System.err.println("--------------------------------------");
        System.err.println("消费端Payload: " + message.getPayload());
        Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        channel.basicAck(deliveryTag, false);
    }


    //订单支付成功--->订单入库消息接收并入库
    /**
     *队列和交换机的基本配置信息在yml里配置，然后再注解中直接引入
     *     spring.rabbitmq.listener.order.queue.name=queue-2
     spring.rabbitmq.listener.order.queue.durable=true
     spring.rabbitmq.listener.order.exchange.name=exchange-1
     spring.rabbitmq.listener.order.exchange.durable=true
     spring.rabbitmq.listener.order.exchange.type=topic
     spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions=true
     spring.rabbitmq.listener.order.key=springboot.*
     * @param order
     * @param channel
     * @param headers
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.order.queue.name}",
                    durable="${spring.rabbitmq.listener.order.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.order.exchange.name}",
                    durable="${spring.rabbitmq.listener.order.exchange.durable}",
                    type= "${spring.rabbitmq.listener.order.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.order.key}"
    )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload Order order,
                               Channel channel,
                               @Headers Map<String, Object> headers) throws Exception {
        System.err.println("--------------------------------------");
        System.err.println("消费端order: " + order.getId());
        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        channel.basicAck(deliveryTag, false);
    }


}
