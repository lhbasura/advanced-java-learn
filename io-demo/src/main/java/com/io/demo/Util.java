package com.io.demo;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Util {
    public static void write(Socket socket) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        new Thread(() -> {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine() + "\r\n";
                try {
                    writer.write(str);
                    writer.flush();
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
