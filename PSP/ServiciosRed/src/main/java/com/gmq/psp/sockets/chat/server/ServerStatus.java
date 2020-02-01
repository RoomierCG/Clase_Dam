package com.gmq.psp.sockets.chat.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerStatus implements Runnable {

    private DataInputStream in;
    Socket client;

    public ServerStatus(Socket client) {
        this.client = client;
        try {
            in = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            while(true){
                String msg  = in.readUTF();
                if(!msg.isEmpty()){
                    System.out.println(msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
