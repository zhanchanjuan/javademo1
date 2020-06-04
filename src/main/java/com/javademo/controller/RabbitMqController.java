package com.javademo.controller;


import com.javademo.common.basic.dto.rabbitMq.ApplicationMqConsumer;
import com.javademo.common.basic.dto.rabbitMq.ApplicationMqProducer;
import com.javademo.service.RabbitMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @author shuyi
 * @date 2020/5/19
 */
@Slf4j
@RestController
@RequestMapping("/rabbit")
public class RabbitMqController {
    @Autowired
    private RabbitMqService rabbitMqService;

    @Autowired
    private ApplicationMqProducer applicationMqProducer;

    @Autowired
    private ApplicationMqConsumer applicationMqConsumer;

    @GetMapping(value = "/rabMqSent")
    public void testMqSent(){
        rabbitMqService.testRabbitMq();
    }

    @PostMapping(value = "/send")
    public void appMsgSend(@RequestBody Map<String,Object> map) throws Exception {
        applicationMqProducer.send(map);
        log.info("消息发送成功111111111");
    }

    @GetMapping(value = "/consume")
    public void appMsgConsume(String clientId) throws Exception {
        applicationMqConsumer.creatChannel(clientId);
    }

}
