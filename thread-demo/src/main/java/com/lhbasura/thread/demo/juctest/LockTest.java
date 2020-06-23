package com.lhbasura.thread.demo.juctest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String []args)
    {

        Lock lock=new ReentrantLock();
        new Thread(()->{
            lock.lock();
            System.out.println("I locked the lock");
            lock.unlock();
            System.out.println("I unlock the lock");
        }).start();
    }

}
