package com.gmq.psp.sockets.chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static com.gmq.psp.sockets.chat.server.Server.users;

public class Client {
    public static String clientName;

    private static String clientAdress, clientOutput;
    private static int clientIp;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket client;
    Runnable  write;
    private Scanner scanner = new Scanner(System.in);

    public void start(){
        try {
            client = new Socket(clientAdress,clientIp);
            mensages();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mensages() {
        write = () -> {
            try {
                out = new DataOutputStream(client.getOutputStream());
                while(true){
                    clientOutput = scanner.nextLine();
                    out.writeUTF(this.clientOutput+": "+clientOutput);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        write.run();
    }

    public void userRewiew(){
        for (int i = 0; i <users.size() ; i++) {
            if (users.get(i).equals(this.clientName)){
                try{
                    System.out.println("Cliente Existente...");
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void write(DataInputStream in){
        Runnable writeMsg = () -> {
            try {
                String mensaje = in.readUTF();
                System.out.println(mensaje);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        writeMsg.run();
    }


    public static String getClientName() {
        return clientName;
    }

    public static void setClientName(String clientName) {
        Client.clientName = clientName;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public Client (Socket client, String clientName){
        this.client = client;
        this.clientName = clientName;
    }

    public Client(String clientAdress, int clientIp, String clientName) {
        this.clientAdress = clientAdress;
        this.clientIp = clientIp;
        this.clientName = clientName;

        userRewiew();
        Client client = new Client(this.client,this.clientName);

        users.add(client);
    }
}
