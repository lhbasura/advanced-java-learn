package com.lhbasura.spring.circle.dependency.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class App {
    @Bean(name = "a")
    public A a(){
        return new A();
    }
    @Bean(name = "b")
    public B b(){
        return new B();
    }

}
