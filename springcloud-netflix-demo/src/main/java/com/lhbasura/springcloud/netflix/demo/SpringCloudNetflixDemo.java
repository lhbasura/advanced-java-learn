package com.lhbasura.springcloud.netflix.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class SpringCloudNetflixDemo {
    public static void main(String[]args){
        SpringApplication.run(SpringCloudNetflixDemo.class, args);
    }
}
