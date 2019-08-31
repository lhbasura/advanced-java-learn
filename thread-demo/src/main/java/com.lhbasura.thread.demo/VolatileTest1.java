package com.lhbasura.thread.demo;

/**
 * volatile关键字保证可见性
 */
public class VolatileTest1 {
    /*
    volatile关键字可以保证线程之间的可见性，当一个线程对变量的值修改时，
    会立即更新到主存（此操作加锁），更新后会让其他线程中局部变量表的值失效
    此时其他线程操作该值时会再去主存中读取,此处如果不加volatile关键字，则
    其他线程无法感知变量的修改，还是采用其线程独占区中局部变量表的值
     */
    private static volatile boolean isGo = false;

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            System.out.println("is enter thread");
            //如果isGo不加volatile修饰这里就会一直读取线程局部变量表中的false造成死循环
            while (!isGo) {
            }
            System.out.println("thread is end");
        });
        thread1.start();

        Thread.sleep(2000);
        Thread thread2 = new Thread(() -> {
            System.out.println("is enter thread 1");
            isGo = true;
            System.out.println("thread 1 is end");

        });
        thread2.start();
    }

}
