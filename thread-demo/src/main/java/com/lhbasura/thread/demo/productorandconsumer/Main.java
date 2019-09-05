package com.lhbasura.thread.demo.productorandconsumer;

public class Main {
    public static void main(String[]args)
    {
        Clerk clerk=new Clerk();
        Consumer consumer=new Consumer(clerk);
        Productor productor=new Productor(clerk);
        new Thread(productor).start();
        new Thread(consumer).start();
    }

}
