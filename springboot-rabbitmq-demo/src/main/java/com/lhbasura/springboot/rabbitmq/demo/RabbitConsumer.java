package com.lhbasura.springboot.rabbitmq.demo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author asura
 * @date 2020/6/5 18:23
 * @desc 队列消费者
 */

@Component
@RabbitListener(queues = "helloRabbit")
public class RabbitConsumer {

    public void process(String content)
    {
        System.out.println("Consumer:"+content);
    }
}
