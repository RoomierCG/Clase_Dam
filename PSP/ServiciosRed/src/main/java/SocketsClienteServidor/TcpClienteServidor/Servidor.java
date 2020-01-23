package SocketsClienteServidor.TcpClienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Servidor {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;

        //Creamos un servidor Con un puerto y mediante la clase InetAddress cogeremos el localhost
        try {
            serverSocket = new ServerSocket(9090);
            Socket wait;

            while(true){
                wait = serverSocket.accept();

                DataInputStream dataInputStream = new DataInputStream(wait.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(wait.getOutputStream());

                dataOutputStream.writeUTF("hola soy el servidor");
                System.out.println(dataInputStream.readUTF());

                serverSocket.close();
                wait.close();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
