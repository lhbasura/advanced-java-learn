package com.lhbasura.spring.test;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public static TestService _ins = new TestService();

    public static TestService getIns(){
        return  _ins;
    }
    private TestService(){
    }
}
