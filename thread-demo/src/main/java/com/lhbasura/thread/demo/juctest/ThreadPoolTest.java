package com.lhbasura.thread.demo.juctest;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author asura
 * @date 2020/6/23 17:27
 * @description
 * ExecutorService.shutdown()禁止线程加入
 * ExecutorService.shutdownNow() 相当于ExecutorService.shutdown+Thread.interrupt
 * ExecutorService.awaitTermination(timeout,unit) 在timeout时间内阻塞，若timeout时间内线程池关闭则直接返回true，timeout时间后返回线程池是否关闭
 */
public class ThreadPoolTest {

    @Test
    public void shutdownTest() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.submit(() -> {
            System.out.println("this is a thread in CachedThreadPool");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread is end");

        });
        cachedThreadPool.shutdown();//会禁止线程submit，当线程执行完后关闭线程池

        try {
            boolean result = cachedThreadPool.awaitTermination(10, TimeUnit.SECONDS);//这里理论上会阻塞<=线程运行的时间
            if(result) {
                System.out.println("thread pool is shutdown");
            }else {
                System.out.println("thread pool is not shutdown");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shutdownNowTest() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.submit(() -> {
            System.out.println("this is a thread in CachedThreadPool");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread is end");

        });
        cachedThreadPool.shutdownNow();//会禁止线程submit，且对当前线程池中的线程调用interrupt,当线程执行完后关闭线程池
        try {
            boolean result = cachedThreadPool.awaitTermination(2, TimeUnit.MINUTES);//这里由于线程池调用了shutdownNow，结束了sleep状态，所以理论上会直接返回true不会发生阻塞
            if(result) {
                System.out.println("thread pool is shutdown");
            }else {
                System.out.println("thread pool is not shutdown");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
