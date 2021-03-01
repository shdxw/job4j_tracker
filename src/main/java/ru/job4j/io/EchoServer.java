package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        if (str.matches(".+=Bye.+")) {
                            return;
                        }
                        System.out.println(str);
                    }

                    out.write("HTTP/1.1 200 OK\r\n".getBytes());
//                    boolean read = false;
//                    while (!(str = in.readLine()).isEmpty()) {
//                        System.out.println(str);
//                        String message = null;
//                        if (!read) {
////                            message = str.split(" ")[1].split("=")[1];
////                            switch (message) {
////                                case ("Hello"):
////                                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
////                                    out.write("Hello, dear friend.".getBytes());
////                                    break;
////                                case ("Bye"):
////                                    return;
////                                default:
////                                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
////                                    out.write("What do y want".getBytes());
////                                    break;
////                            }
//                        }
//                        read = true;
//                    }
//                    System.out.println("~~~~~~");
//                    read = false;
                }
            }
        } catch (IOException e) {
            LOG.error("IO ERROR: ", e);
        }
    }
}
