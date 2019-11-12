package com.company;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket mSocket;
    private BufferedReader mIn;
    private PrintWriter mOut;

    public Client(String ip, int port) {
        try {
            mSocket = new Socket(ip,port);

            while (true) {
                mIn = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
                mOut = new PrintWriter(mSocket.getOutputStream());

                //메세지 전달
                mOut.println("응답하라!!");
                mOut.flush();

                //응답 출력
                System.out.println(mIn.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                mSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        // write your code here172.30.1.47

        new Client("192.168.0.12",8000);
    }
}
