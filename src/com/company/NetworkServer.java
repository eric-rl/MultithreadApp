package com.company;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

public class NetworkServer implements Runnable {

    public final int PORT = 80;
    private final int SLEEP_MS = 100;
    private final int MSG_SIZE = 512;
    private DatagramSocket socket;
    private boolean isRunning = true;
    String myData;

    public NetworkServer(){
        try {
            Thread ServerThread = new Thread(this::run);
//            this.run();
            ServerThread.start();
            socket = new DatagramSocket(PORT);
            socket.setSoTimeout(SLEEP_MS);
        } catch(SocketException e){ System.out.println(e.getMessage()); }
    }


    public void sendMsgToClient(String msg, SocketAddress clientSocketAddress) {
        byte[] buffer = msg.getBytes();

        DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientSocketAddress);

        try {
            socket.send(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (isRunning) {
            DatagramPacket clientRequest = new DatagramPacket(new byte[MSG_SIZE], MSG_SIZE);

            if (!receieveMsgFromAnyClient(clientRequest)) {
                continue;
            }
            String clientMsg = new String(clientRequest.getData(), 0, clientRequest.getLength());
            System.out.println("Server says: " + clientMsg); // debugging purpose only!
            sendMsgToClient(clientMsg, clientRequest.getSocketAddress());
            // TODO: Save the msg to a queue instead

        }
    }

    private boolean receieveMsgFromAnyClient(DatagramPacket clientRequest) {
        try {
            socket.receive(clientRequest);
            serverReceievedConfirmation(clientRequest);

        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public void serverReceievedConfirmation(DatagramPacket clientRequest) {
        this.sendMsgToClient("Message received", clientRequest.getSocketAddress());
    }
}