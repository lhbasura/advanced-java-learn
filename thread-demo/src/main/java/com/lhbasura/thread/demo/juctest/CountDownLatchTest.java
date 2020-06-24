package com.lhbasura.thread.demo.juctest;

import java.util.concurrent.CountDownLatch;

/**
 * @author asura
 * @date 2020/6/24 10:15
 * @description CountDownLatch先预设一个线程数量n，当线程调用
 * CountDownLatch的countDown方法时n-1并阻塞调用线程,直到n=0时停止阻塞
 */

public class CountDownLatchTest {
    private static final int threadNum=10;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
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
