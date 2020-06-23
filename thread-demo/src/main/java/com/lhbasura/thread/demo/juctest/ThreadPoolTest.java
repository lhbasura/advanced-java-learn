package com.lhbasura.thread.demo.juctest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author asura
 * @date 2020/6/23 17:27
 * @description
 */
public class ThreadPoolTest {
    public static void main(String[]args) {

        ExecutorService cachedThreadPool= Executors.newCachedThreadPool();
        cachedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is a new CachedThreadPool");
            }
        });
        boolean hasWork=true;
        while (hasWork) {
            hasWork=!cachedThreadPool.isShutdown();
        }
        System.out.println("thread pool is shutdown");
    }
}
