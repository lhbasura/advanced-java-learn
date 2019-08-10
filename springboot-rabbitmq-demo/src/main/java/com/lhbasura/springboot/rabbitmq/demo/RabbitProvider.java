package com.lhbasura.springboot.rabbitmq.demo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class RabbitProvider {

    @Resource
    private AmqpTemplate amqpTemplate;

    public void send() {
        String content = "Hello " + new Date();
        System.out.println("Provider:" + content);
        amqpTemplate.convertAndSend("helloRabbit", content);
    }
}
