package com.lhbasura.springboot.rabbitmq.demo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author asura
 * @date 2020/6/5 18:24
 * @desc 队列生产者
 */

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
