package com.lhbasura.thread.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    private static int threadNum = 10;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum, new Runnable() {
            @Override
            public void run() {
                System.out.println("all child thread is end");
            }
        });
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "--> is start");
                try {
                    cyclicBarrier.await();//当线程数量达到threadNum时停止阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "--> is end");
            }).start();
        }
        System.out.println("main thread is end");
    }
}
