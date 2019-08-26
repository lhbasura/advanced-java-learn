package com.io.demo.asyncnonblock;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 异步非阻塞
 */
public class Server {
    private static final int BUF_SIZE=1024;
    private static final int PORT = 8888;
    private static final int TIMEOUT = 3000;
    public static void main(String[]args) throws IOException {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.bind(new InetSocketAddress(PORT));
        Selector selector=Selector.open();
        socketChannel.configureBlocking(false); // 必须设置为非阻塞
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true)
        {

        }
    }
}
