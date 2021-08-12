package com.lhbasura.springcloud.netflix.demo.hystrix.controller;

import com.lhbasura.springcloud.netflix.demo.hystrix.service.TestHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RestController
public class TestHystrixController {
    @Resource
    TestHystrixService hystrixService;
    @GetMapping("/testHystrix")
    public String test(){
        return hystrixService.normalDeal();
    }

}
