package com.gmq.psp.sockets.chat.client;

public class ClientAplication {

    private static int clientIp = 9090;
    private static String clientAdress = "127.0.0.1";
    private static String clientName;

    public static void main(String[] args) {
        clientName = args[0];

        Client client = new Client(clientAdress,clientIp,clientName);
        client.start();
    }
}
