package com.lhbasura.spring.aop.demo.aspect;

import com.alibaba.fastjson.JSONObject;
import com.lhbasura.spring.aop.demo.annotation.SayHello;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
//lombok日志
@Slf4j
public class LogAspect {

    //execution表达式自行搜索引擎
    @Pointcut("execution(* com.lhbasura.spring.aop.demo.controller.UserController.getInfo(..))")
    public void pointcut() {
    }

    @Pointcut("execution(* com.lhbasura.spring.aop.demo.controller.UserController.getName(..))")
    public void pointcut2() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        //获取请求的方法
        Signature sig = joinPoint.getSignature();
        String method = joinPoint.getTarget().getClass().getName() + "." + sig.getName();

        //获取请求的参数
        Object[] args = joinPoint.getArgs();
        //fastjson转换
        String params = JSONObject.toJSONString(args);

        //打印请求参数
        log.info("this is before log," + method + ":" + params);
    }

    @After("pointcut2()")
    public void after(JoinPoint joinPoint) {
        //获取请求的方法
        Signature sig = joinPoint.getSignature();
        String method = joinPoint.getTarget().getClass().getName() + "." + sig.getName();

        //获取请求的参数
        Object[] args = joinPoint.getArgs();
        //fastjson转换
        String params = JSONObject.toJSONString(args);

        //打印请求参数
        log.info("this is after log," + method + ":" + params);
    }

    @After("@annotation(test)")
    public void annotationTest(JoinPoint joinPoint, SayHello test) {
        log.info("hello " + test.name());
    }
}
