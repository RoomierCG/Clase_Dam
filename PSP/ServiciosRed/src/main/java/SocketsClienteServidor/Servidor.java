package SocketsClienteServidor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Servidor {

    private static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {

        //Creamos un servidor Con un puerto y mediante la clase InetAddress cogeremos el localhost
        try {
            serverSocket = new ServerSocket(9090, 0, InetAddress.getByName("localhost"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        //Ahora recogeremos la conexion
        Socket accept = serverSocket.accept();
        DataOutputStream outPutStream = new DataOutputStream(accept.getOutputStream());

        //Escribimos con el servidor y los clientes lo recibiran cuando realicen una conexion
        for (int i = 0; i < 11; i++) {
            String sMensaje = "Hola, soy el paquete: " + i + "\n";
            outPutStream.write(sMensaje.getBytes());
        }

        System.out.println("Envio con exito");
        outPutStream.close();
        serverSocket.close();
    }
}
