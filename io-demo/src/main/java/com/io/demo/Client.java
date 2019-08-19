package com.io.demo;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            Util.read(socket);
            Util.write(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
