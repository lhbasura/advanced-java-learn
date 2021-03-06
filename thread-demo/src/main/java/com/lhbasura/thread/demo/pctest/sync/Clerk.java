package com.lhbasura.thread.demo.pctest.sync;

import java.util.HashMap;

public class Clerk {
    private int product = 0;
    private static final int space = 10;

    public synchronized void sell() throws InterruptedException {
        while (product <= 0) {
            System.out.println("库存不足");
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + "消费了一个产品,还剩" + (--product) + "个");
        this.notifyAll();
    }

    public synchronized void get() throws InterruptedException {
        while (product >= space) {
            System.out.println("仓库满了");
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + "生产了一个产品,还剩" + (++product) + "个");
        this.notifyAll();
    }
}
