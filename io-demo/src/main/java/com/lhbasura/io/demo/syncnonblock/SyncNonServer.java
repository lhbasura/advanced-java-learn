package com.lhbasura.io.demo.syncnonblock;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author asura
 * @date 2020/6/12 15:30
 * @description 同步非阻塞IO
 */

public class SyncNonServer {

    private static final int BUF_SIZE = 1024;
    private static final int PORT = 8888;
    private static final int TIMEOUT = 1000;

    private static void doSomething() {
        System.out.println("这里是后继操作");
    }

    ServerSocketChannel socketChannel;
    Selector selector = null;

    public SyncNonServer() {
        this.init(PORT);
    }

    private void init(int port) {
        try {
            socketChannel = ServerSocketChannel.open();
            socketChannel.socket().bind(new InetSocketAddress(port));
            socketChannel.configureBlocking(false);
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
        while (true) {
            i++;
            try {
                if (selector.select(TIMEOUT) == 0) {
                  //  System.out.println("==>" + i);
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                try {
                    if (key.isAcceptable()) {
                        handleAccept(key);
                    }
                    if (key.isReadable()) {
                        handleRead(key);
                    }
                    if ( key.isValid()) {
                        handleWrite(key);
                    }
                    if (key.isConnectable()) {
                        System.out.println("isConnectable = true");
                    }
                } catch (IOException exception) {
                    key.cancel();
                    exception.printStackTrace();
                }
                iter.remove();
                doSomething();
            }
        }
    }

    public static void main(String[] args) {
        new SyncNonServer();
    }

    void handleAccept(SelectionKey key) throws IOException {
        System.out.println("一个客户端连接了");
        ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssChannel.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
    }

    void handleRead(SelectionKey key) throws IOException {
        System.out.println("发生了读操作");
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buf = ByteBuffer.allocate(BUF_SIZE);
        long bytesRead = sc.read(buf);
        while (bytesRead > 0) {
            buf.flip();
            System.out.println(new String(buf.array()));
            bytesRead = sc.read(buf);
                    try {
                sc.write(buf);// 将消息回送给客户端
                System.out.println("send ok");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        if (bytesRead == -1) {
            sc.close();
        }

    }

    void handleWrite(SelectionKey key) throws IOException {

//            ByteBuffer buf = ByteBuffer.allocate(BUF_SIZE);
//            String str="ss";
//            buf.put(str.getBytes());
//            buf.flip();
//            SocketChannel sc = (SocketChannel) key.channel();

//        try {
//                sc.write(buf);// 将消息回送给客户端
//                System.out.println("send ok");
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
    }

}
