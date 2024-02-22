package com.example.hospital_manament;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {


            try {
            ServerSocket ss = new ServerSocket(8818);
            System.out.println("Server Started\n");

           while (true) {
                   Socket s = ss.accept();
                   System.out.println("Client Connect to Server");

                   ServerHandler handler = new ServerHandler(s);
                   System.out.println(handler.getName());
                   handler.start();
           }


            }catch (IOException e) {
            System.out.println("Problem Caught in Server Catch Clause");
            }


    }
}
