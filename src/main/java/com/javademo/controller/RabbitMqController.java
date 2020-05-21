package com.javademo.controller;


import com.javademo.service.RabbitMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author shuyi
 * @date 2020/5/19
 */
@Slf4j
@RestController
@RequestMapping("rabbit")
public class RabbitMqController {
    @Autowired
    private RabbitMqService rabbitMqService;

    @GetMapping(value = "/rabMqSent")
    public void testMqSent(){
        rabbitMqService.testRabbitMq();
    }




}
