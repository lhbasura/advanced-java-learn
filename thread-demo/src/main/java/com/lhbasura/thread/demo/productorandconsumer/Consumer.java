package com.lhbasura.thread.demo.productorandconsumer;

public class Consumer implements Runnable {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i=0;i<2;i++)
        {
            try {
                clerk.sell();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
