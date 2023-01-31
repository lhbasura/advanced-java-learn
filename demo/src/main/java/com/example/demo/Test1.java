package com.example.demo;

public class Test1 {
    public static Test1 _ins = new Test1();

    Test2 test2 = Test2.getIns();
    public static Test1 getIns(){
        return  _ins;
    }
    private Test1(){
        System.out.println("test 1 is init");
    }

    public void test(){
        System.out.println("test1");
    }
    public void doTest(){
        test2.doTest();
    }
}
