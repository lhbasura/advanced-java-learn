package com.lhbasura.io.demo.asyncnonblock.aio;

import lombok.Data;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

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
    private AsynchronousServerSocketChannel serverSocketChannel;

    private static void doSomething() {
        System.out.println("这里是后继操作");
    }

    public Server() {
        this.init(PORT);
    }

    private void init(int port) {
        try {
            serverSocketChannel = AsynchronousServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.accept(this, new AcceptHandler());
            int i = 0;
            while (true) {
                i++;
                Thread.sleep(1000);
                System.out.println(">>>>>" + i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Server();

    }
}
