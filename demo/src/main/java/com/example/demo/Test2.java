package com.example.demo;

public class Test2 {
    public static Test2 _ins = new Test2();

    Test1 test1 = Test1.getIns();
    public static Test2 getIns(){
        return  _ins;
    }
    private Test2(){
        System.out.println("test 2 is init");
    }

    public void test(){
        System.out.println("test2");
    }
    public void doTest(){
        test1.test();
    }
}
