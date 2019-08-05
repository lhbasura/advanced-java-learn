package com.lhbasura.springbootthreadpooldemo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    @Async
    public void execute() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread thread = Thread.currentThread();
                System.out.println("thread" + thread.getId() + ":" + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
