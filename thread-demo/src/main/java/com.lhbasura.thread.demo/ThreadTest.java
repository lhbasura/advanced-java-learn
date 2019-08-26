package com.lhbasura.thread.demo;

public class ThreadTest {
    private static volatile boolean isGo = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("is enter thread");
            while (!isGo) {
            }
            System.out.println("thread is end");
        });
        thread.start();

        Thread.sleep(2000);
        Thread thread1 = new Thread(() -> {
            System.out.println("is enter thread 1");
            isGo = true;
            System.out.println("thread 1 is end");

        });
        thread1.start();
    }

}
