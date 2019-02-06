package com.company;

import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        NetworkClient networkClient1 = new NetworkClient();
        NetworkServer networkServer1 = new NetworkServer();

        Thread netWorkThread = new Thread(networkServer1);
        netWorkThread.start();

        networkClient1.sendMsgToServer("hej");




    }
}
