package com.lhbasura.spring.circle.dependency.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringCircleDependencyDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        A a =(A) context.getBean("a");
        B b =(B) context.getBean("b");

        System.out.println(a);
        System.out.println(a.getB());
        System.out.println(a.getB().getA());
    }
}
