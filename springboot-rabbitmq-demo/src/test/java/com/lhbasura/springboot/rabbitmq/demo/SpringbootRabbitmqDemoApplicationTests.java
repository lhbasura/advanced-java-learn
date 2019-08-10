package com.lhbasura.springboot.rabbitmq.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRabbitmqDemoApplication.class)
public class SpringbootRabbitmqDemoApplicationTests {

    @Resource
    private RabbitProvider rabbitProvider;

    @Test
    public void contextLoads() {
        for (int i = 0; i < 100; i++) {
            rabbitProvider.send();
        }
    }

}
