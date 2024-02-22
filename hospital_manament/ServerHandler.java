package com.example.hospital_manament;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerHandler extends Thread{

    private final Socket clientSocket;

    public ServerHandler(Socket s) {
        this.clientSocket = s;
    }


    @Override
    public void run() {
        try {
            ObjectInputStream ois  = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());


            while (true) {
                Object cMsg = ois.readObject();
                String line = (String) cMsg;

                if (line.equals("out")) {
                    try {
                        oos.writeObject("You Disconnected from Server");
                        System.out.println(Thread.currentThread().getName());
                        System.out.println("Client Logged out");
                        clientSocket.close();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else {
                    System.out.println("Client: " + line);

                    oos.writeObject("You Logged in");
                }

            }//while end


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
