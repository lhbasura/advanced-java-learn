package com.lhbasura.io.demo;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    static  List<Client> list =new ArrayList<>();
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            read(socket);
            write(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步写
     * @param socket
     * @throws IOException
     */
    public static void write(Socket socket) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        new Thread(() -> {
            while (true) {
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
        }).start();
    }

    /**
     * 异步读
     * @param socket
     * @throws IOException
     */
    public static void read(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        new Thread(() -> {
            while (true) {
                String str;
                try {
                    if ((str = reader.readLine()) != null) {
                        System.out.println("\33 you received a messge:" + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
