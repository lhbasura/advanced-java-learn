package com.lhbasura.thread.demo.juctest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/**
 * @author asura
 * @date 2020/6/24 9:52
 * @description CyclicBarrier设置一个线程数量和一个runnable对象，
 * 当线程内调用await方法时，线程会阻塞，值到调用await方法的线程数达到
 * CyclicBarrier设置一个线程数量预设的线程数后停止阻塞，此时会start预设
 * 的runnable
 */

public class CyclicBarrierTest {
    private final static int threadNum = 10;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum, () -> {
            System.out.println("all child thread is ready");
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
