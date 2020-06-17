package com.lhbasura.io.demo.asyncnonblock.aio;

import lombok.Data;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author asura
 * @date 2020/6/12 15:26
 * @description 异步非阻塞IO(AIO实现)
 */
@Data
public class Server {

    private static final int PORT = 8888;
    private static final int TIMEOUT = 3000;
    private static final int THREAD_NUM = 4;
    private ExecutorService executorService;
    private AsynchronousServerSocketChannel serverSocketChannel;

    private static void doSomething() {
        System.out.println("这里是后继操作");
    }

    public Server(){
        this.init(PORT);
    }

    private void init(int port){
        executorService= Executors.newFixedThreadPool(THREAD_NUM);
        try {
            serverSocketChannel=AsynchronousServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.accept(this, new AIOHandler());
            System.out.println(">>>>>");
            try {
                // 阻塞程序，防止被GC回收
                TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        new Server();

    }
}
