package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("서버시작");
            while (true) {
                //스레드가 멈춰 있고
                //연결 요청이 들어오면 연결
                Socket socket = serverSocket.accept();

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                String inStr = inputStream.readUTF();

                System.out.println("inStr from client : " + inStr);

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeUTF(inStr + "from server");
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}