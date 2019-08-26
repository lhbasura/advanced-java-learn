package com.io.demo.asyncblock;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
异步阻塞IO
 */
public class Server {

    private static final int PORT = 8888;


    private static void doSomething() {
        System.out.println("这里是后继操作");
    }

    private static Map<Socket, Integer> clients = new HashMap<>();

    static class Handler implements Runnable {
        Socket socket;
         String str;

        public Handler(Socket socket) {
            this.socket = socket;
            str = "";
        }

        @Override
        public void run() {
            new Thread(() -> {
                while (true) {
                    if (!str.isEmpty()) {
                        System.out.println("收到的信息：" + str);
                        str = "";
                    }
                    doSomething();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (!socket.isClosed()) {
                try {
                    // 这里读操作是阻塞的
                    str = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);


        while (true) {
            Socket socket = serverSocket.accept(); // 阻塞在此处(这里accept没有做异步）
            System.out.println("一个客户端连接了");
            ExecutorService executorService = Executors.newCachedThreadPool();
            // 多线程实现异步读取数据
            Handler handler = new Handler(socket);
            executorService.execute(handler);
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
