package com.company;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class SenderThread extends Thread {
    Socket socket;

    public SenderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream());


                outputStream.writeUTF("welcome");
//                writer.println(str);
                writer.flush();

        }


        catch(Exception e) {

        }

        finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }

    }

}


class ReceiverThread extends Thread {
    Socket socket;

    public ReceiverThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            while (true) {
                String str = inputStream.readUTF();
                if(str == null){
                    break;
                }
                System.out.println("수신>" + str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

public class Server_1_1 {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("서버연결");

            while(true) {
                socket = serverSocket.accept();

                Thread thread1 = new SenderThread(socket);
                Thread thread2 = new ReceiverThread(socket);

                thread1.start();
                thread2.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}


