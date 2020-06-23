package com.lhbasura.thread.demo.juctest;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    private static int threadNum;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "--> is start");
                countDownLatch.countDown();//通过coutDown函数计数
                System.out.println(Thread.currentThread().getName() + "--> is end");
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thead is end");

    }
}
