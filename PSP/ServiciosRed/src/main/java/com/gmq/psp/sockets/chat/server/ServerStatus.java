package com.gmq.psp.sockets.chat.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerStatus implements Runnable{

   DataInputStream in;
   Socket client;
   String clientName;
    ServerStatus serverClient;
    List<ServerStatus> arrUsers = new ArrayList<>();

    @Override
    public void run() {
        checkUsers();
        try {
            in = new DataInputStream(client.getInputStream());
            String response = in.readUTF();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ServerStatus checkUsers(){
        for (int i = 0; i < arrUsers.size(); i++){
            if (!arrUsers.get(i).getClientName().equals(this.clientName)) {
                ServerStatus arrClient = new ServerStatus(this.client,this.clientName);
                return arrClient;
            }else {
                return null;
            }
        }
        return null;
    }

    public String getClientName() {
        return clientName;
    }

    public ServerStatus(Socket client) {
        this.client = client;
    }

    public ServerStatus(Socket client, String clientName){
        this.client = client;
        this.clientName = clientName;
    }
}
