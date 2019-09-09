package com.lhbasura.spring.circle.dependency.demo;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class B {
    public String msg = "this is B";
    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    @Resource(name = "a")
    private A a;

    @Override
    public String toString() {
        return "B{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
