package com.lhbasura.spring.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InitService {
    public static InitService _ins = new InitService();

    public static InitService getIns(){
        return  _ins;
    }
//    private TestService(){
//    }
    public void init(){
        SpringApplication.run(InitService.class, new String[]{});
    }
}
