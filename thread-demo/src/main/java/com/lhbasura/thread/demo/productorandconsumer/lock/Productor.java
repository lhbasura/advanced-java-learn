package com.lhbasura.thread.demo.productorandconsumer.lock;

public class Productor implements Runnable {
    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++)
        {
            try {
                clerk.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
