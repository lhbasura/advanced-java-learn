package com.io.demo.asyncblock;

import com.io.demo.Client;
import com.io.demo.Util;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class Server {

    private static final int BUF_SIZE = 1024;
    private static final int PORT = 8888;

    static private Map<Socket,Integer> clients=new HashMap<>();

    public static void doSomething() {
        System.out.println("这里是后继操作");
    }


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);


        while (true) {
            Socket socket = serverSocket.accept();//阻塞在此处
            System.out.println("一个客户端连接了");

            // 多线程实现异步
            read(socket);
            write(socket);
        }
    }

    public static void write(Socket socket) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        new Thread(() -> {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine() + "\r\n";
                try {
                    writer.write(str);
                    writer.flush();
                    doSomething(); //后继操作
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void read(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        new Thread(() -> {
            while (true) {
                String str;
                try {
                    if ((str = reader.readLine()) != null) // readLine为阻塞操作
                    {
                        System.out.println("\33 you received a messge:" + str);
                        doSomething(); //后继操作
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
