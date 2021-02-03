package com.lhbasura.thread.demo.volatiletest;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;

/*
volatile关键字保证操作有序性（禁止指令重排）
 */
public class VolatileTest2 {

    static  int i;
    static  int j;

    public static void main(String[] args) throws InterruptedException {
        Set<String> set = new HashSet();
        Map<String, Integer> map = new HashMap();
        for (int k = 0; k < 1000000; k++) {
            i = 5;
            j = 100;
            map.clear();
            Thread thread1 = new Thread(() -> {
                int a = i;
                j = 1;
                map.put("a", a);
            });
            Thread thread2 = new Thread(() -> {
                int b = j;
                i = 1;
                map.put("b", b);
            });
            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();
            set.add("{a="+map.get("a")+",b="+map.get("b")+"}");
            System.out.println(set);
        }
    }

}
