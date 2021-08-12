package com.lhbasura.springcloud.netflix.demo.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

@Service
public class TestHystrixService {
    @HystrixCommand(fallbackMethod = "exceptDeal")
    public String normalDeal()  {
        int i=10;
        while (i>0){

        }
        return "this is a normal return";
    }

    public String exceptDeal(){
        return "this is a except deal";
    }



}
