package com.lhbasura.thread.demo.productorandconsumer.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Clerk {
    private int product = 0;
    private static final int space = 10;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    public void sell() throws InterruptedException {
        lock.lock();
        while (product <= 0) {
            System.out.println("库存不足");
            condition.await();
        }
        System.out.println(Thread.currentThread().getName() + "消费了一个产品,还剩" + (--product) + "个");
        condition.signalAll();
        lock.unlock();
    }

    public void get() throws InterruptedException {
        lock.lock();
        while (product >= space) {
            System.out.println("仓库满了");
            condition.await();
        }

        System.out.println(Thread.currentThread().getName() + "生产了一个产品,还剩" + (++product) + "个");
        condition.signalAll();
        lock.unlock();
    }
}
