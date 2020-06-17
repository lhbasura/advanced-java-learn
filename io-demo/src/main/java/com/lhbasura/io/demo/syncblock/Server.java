package com.lhbasura.io.demo.syncblock;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author asura
 * @date 2020/6/12 15:30
 * @description 同步阻塞IO
 */

public class Server {

    private static final int PORT = 8888;

    public static void doSomething() {
        System.out.println("这里是后继操作");
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            // 这里连接是阻塞的，accept实质上也进行了IO操作，三次握手的本质是传输是TCP数据包
            Socket client = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (!client.isClosed()) {
                String str;
                try {
                    // 这里读操作是阻塞的
                    if ((str = reader.readLine()) != null) {
                        System.out.println("\33 you received a messge:" + str);
                    }
                    doSomething(); // 后继操作
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
