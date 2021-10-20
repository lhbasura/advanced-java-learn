package com.lhbasura.spring.test;

public class App {
    public static void main(String[]args){
        InitService initService = InitService.getIns();
        initService.init();

    }
}
