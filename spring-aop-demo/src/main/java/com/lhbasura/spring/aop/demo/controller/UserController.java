package com.lhbasura.spring.aop.demo.controller;

import com.lhbasura.spring.aop.demo.annotation.SayHello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    @GetMapping("/getInfo")
    public String getInfo(String name, Integer age) {
        log.info("go the url /getInfo");
        return "name:" + name + "age:" + age;
    }

    @GetMapping("/getName")
    @SayHello(name = "world")
    public String getName(String name) {
        log.info("go the url /getInfo");
        return "name:" + name;
    }

}
