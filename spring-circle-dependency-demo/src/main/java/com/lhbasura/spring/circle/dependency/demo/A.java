package com.lhbasura.spring.circle.dependency.demo;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class A {
    String msg = "this is a";
    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    @Resource(name = "b")
    private B b;

    @Override
    public String toString() {
        return "A{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
