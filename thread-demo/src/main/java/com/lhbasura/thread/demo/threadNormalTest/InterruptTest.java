package com.lhbasura.thread.demo.threadNormalTest;

/**
 * @author asura
 * @date 2020/6/24 11:43
 * @description interrupt 用于给线程设置一个中断标记位，让
 * 调用sleep，wait等方法（需要是抛出InterruptedException的方法）
 * 等阻塞状态的线程抛出一个InterruptedException。（注：interrupt不会中断正在运行的线程）
 */
public class InterruptTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("thread 1 is interrupted");
                e.printStackTrace();
            }
            System.out.println("thread 1 is end");
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000000000; i++) {
                System.out.println("thread2 for " + i);
            }
            System.out.println("thread 2 is end");
        });
        thread1.start();
        thread2.start();
        System.out.println("to do thread1 interrupt");
        thread1.interrupt();
        System.out.println("to do thread2 interrupt");
        thread2.interrupt();
    }
}
