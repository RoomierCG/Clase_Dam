package SocketsClienteServidor.TcpClienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1",9090);
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
            DataInputStream inputStream = new DataInputStream(client.getInputStream());

            //recibo del servidor
            System.out.println(inputStream.readUTF());
            Thread.sleep(5000);

            //Envio un mensaje al cliente
            String mensaje = "hola soy el cliente";
            dataOutputStream.writeUTF(mensaje);

            dataOutputStream.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
