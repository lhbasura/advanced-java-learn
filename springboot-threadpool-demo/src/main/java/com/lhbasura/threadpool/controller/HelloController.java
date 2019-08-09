package com.lhbasura.threadpool.controller;

import com.lhbasura.threadpool.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Slf4j
@Controller
public class HelloController {
    @Resource
    HelloService helloService;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        helloService.execute();
        helloService.execute();
        return "hello";
    }
}
