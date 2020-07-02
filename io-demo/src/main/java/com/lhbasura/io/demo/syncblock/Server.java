package com.lhbasura.io.demo.syncblock;

import lombok.SneakyThrows;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    private ServerSocket serverSocket;

    public Server() {
        this.init(PORT);
    }

    public void init(int port) {
        try {
            serverSocket = new ServerSocket(port);
            /*
            这里接收一个客户端socket，由于只接受一次
            所以只能处理一个链接，如果把这句话放到while里面，由于
            accept是阻塞的，当处理完第一个socket发送的数据后就会阻塞在
            accept方法，从而无法进行任何操作
            这就是为什么说同步阻塞IO是无法进行多客户端通信的原因
           */
            Socket client = serverSocket.accept();
            while (true) {
                if (client.isConnected()) {
                    System.out.println(">>>一个客户端连接了");
                }

                // 读写都是阻塞的
                handleRead(client);
                handleWrite(client);

                // 后继操作
                doSomething();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void handleRead(Socket socket) {
        String str;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 这里读操作也是阻塞的
            if ((str = reader.readLine()) != null) {
                System.out.println("\33 you received a messge:" + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void handleWrite(Socket socket) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine() + "\r\n";
        try {
            writer.write(str);
            writer.flush();
            System.out.println("send ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Server();
    }
}
