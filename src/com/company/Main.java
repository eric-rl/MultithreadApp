package com.company;

import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        NetworkClient networkClient1 = new NetworkClient();
        NetworkServer networkServer1 = new NetworkServer();


        System.out.println("Enter your message");
        Scanner scanner = new Scanner(System.in);
        boolean run =true;
        while (run) {
            networkClient1.sendMsgToServer(scanner.nextLine());
        }
    }
}
