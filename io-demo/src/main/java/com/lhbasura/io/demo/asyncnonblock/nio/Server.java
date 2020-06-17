package com.lhbasura.io.demo.asyncnonblock.nio;


import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author asura
 * @date 2020/6/12 15:29
 * @description 异步非阻塞IO(NIO实现)
 */

public class Server {
    private static final int BUF_SIZE=1024;
    private static final int PORT = 8888;
    private static final int TIMEOUT = 3000;
    private static void doSomething() {
        System.out.println("这里是后继操作");
    }
    public static void main(String[]args) throws IOException {
        ServerSocketChannel socketChannel=ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(PORT));
        socketChannel.configureBlocking(false); // 必须设置为非阻塞
        Selector selector=Selector.open();
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            int i=0;
            while (true) {
                i++;
                if (selector.select(TIMEOUT) == 0) {
                    System.out.println("==>"+i);
                    continue;
                }
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isAcceptable()) {
                        new Thread(()->{
                            try {
                                handleAccept(key);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    if (key.isReadable()) {
                        new Thread(()->{
                            try {
                                handleRead(key);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    if (key.isWritable() && key.isValid()) {
                        new Thread(()->{
                            try {
                                handleWrite(key);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    if (key.isConnectable()) {
                        System.out.println("isConnectable = true");
                    }
                    doSomething();
                    iter.remove();
                }
            }

        }
    }

    static void handleAccept(SelectionKey key) throws IOException {
        System.out.println("一个客户端连接了");
        ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssChannel.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
    }

    static void handleRead(SelectionKey key) throws IOException {
        System.out.println("发生了读操作");
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buf = (ByteBuffer) key.attachment();
        long bytesRead = sc.read(buf);
        while (bytesRead > 0) {
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            System.out.println();
            buf.clear();
            bytesRead = sc.read(buf);
        }
        if (bytesRead == -1) {
            sc.close();
        }

    }

    static void handleWrite(SelectionKey key) throws IOException {

        ByteBuffer buf = (ByteBuffer) key.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while (buf.hasRemaining()) {
            sc.write(buf);
        }
        buf.compact();
    }

}
