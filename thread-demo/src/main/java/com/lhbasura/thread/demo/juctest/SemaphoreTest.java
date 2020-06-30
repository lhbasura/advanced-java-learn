package com.lhbasura.thread.demo.juctest;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author asura
 * @date 2020/6/23 18:43
 * @description Semaphore可以用来对同时运行的线程数量进行限制
 */
public class SemaphoreTest {
    static final int threadNum = 25;//线程数量
    static final int limit = 5; //限制线程数量
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(limit);
        for (int i = 1; i <= threadNum; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread" + finalI);
                semaphore.release();
            });
            thread.start();
        }
    }
}
