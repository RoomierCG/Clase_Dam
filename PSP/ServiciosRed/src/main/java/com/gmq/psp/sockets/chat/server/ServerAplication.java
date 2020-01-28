package com.gmq.psp.sockets.chat.server;

public class ServerAplication {

    private static int serverIp = 9090;

    public static void main(String[] args) {
        Server server = new Server(serverIp);
        server.start();
    }
}
