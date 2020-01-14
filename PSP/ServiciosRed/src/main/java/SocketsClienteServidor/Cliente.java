package SocketsClienteServidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class Cliente {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 9090);
            InputStream inputStream = client.getInputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            int datos;

            while ((datos = inputStreamReader.read()) != -1){
                String dato = String.valueOf((char) datos);
                System.out.print(dato);
            }


            inputStream.close();
            inputStreamReader.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
