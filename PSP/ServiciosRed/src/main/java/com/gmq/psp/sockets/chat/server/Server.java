package com.gmq.psp.sockets.chat.server;

import com.gmq.psp.sockets.chat.client.Client;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private int serverIp;
    public static List<Client> users = new ArrayList<>();
    Socket socketClient;

    public Server(int serverIp) {
        this.serverIp = serverIp;
    }

    public void start(){
        try{
            //Hacemos un servidor en el puerto indicado
            ServerSocket server = new ServerSocket(serverIp);
            //Permitimos entrar hasta 5 usuarios
            ExecutorService pool = Executors.newFixedThreadPool(5);

            while(true){
                System.out.println("Server: Esperando conexiones");
                socketClient = server.accept();

                System.out.println("Usuario Encontrado");
                pool.submit(() -> {
                    reading(socketClient);
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reading(Socket socketClient){
        Runnable msg = () -> {
            try {
                DataInputStream in = new DataInputStream(socketClient.getInputStream());
                String inputData = in.readUTF();

                //todo enviar datos a los clientes
                for (int i = 0; i < users.size(); i++){
                    if (socketClient != users.get(i).getClient()){
                        DataOutputStream out = new DataOutputStream(socketClient.getOutputStream());
                        out.writeUTF(inputData);
                        out.close();
                    }
                }

                //cerramos cosas
                in.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        msg.run();
    }

}
