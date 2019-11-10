package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;

public class Server implements Runnable {
    private Socket mSocket;

    public static void main(String[] args) {
        // write your code here172.30.1.47
        Thread pcThread = new Thread( new Server());
        pcThread.start();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("서버시작");
            //스레드가 멈춰 있고


            while (true) {
                //연결 요청이 들어오면 연결
                mSocket = serverSocket.accept();
                System.out.println("클라이언트와 연결 됨");

                try {
                    //클라이언트에서 보낸 문자열 출력
                    BufferedReader in = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
                    String str = in.readLine();
                    System.out.println("S:Received " + str);


                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream())));
                    out.println("Server Received " + str);
                } catch (Exception e) {
                    System.out.println("에러");
                    e.printStackTrace();
                } finally {
                    mSocket.close();
                    System.out.println("S : Done");
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
