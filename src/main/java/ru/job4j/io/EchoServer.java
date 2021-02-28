package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        String message = str.split(" ")[1];
                        switch (message) {
                            case ("Hello"):
                                out.write("HTTP/1.1 200 OK \r\n\\".getBytes());
                                out.write("Hello, dear friend.".getBytes());
                                break;
                            case ("Exit"):
                                System.exit(0);
                                break;
                            default:
                                out.write("HTTP/1.1 200 OK \r\n\\".getBytes());
                                out.write("What do y want".getBytes());
                                break;
                        }
                        System.out.println(str);
                    }
                }
            }
        }
    }
}
