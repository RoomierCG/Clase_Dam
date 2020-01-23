package SocketsClienteServidor.TcpMultihilo.Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {

    private final int PORT;
    private final String HOSTNAME;

    public Cliente(int PORT, String HOSTNAME) {
        this.PORT = PORT;
        this.HOSTNAME = HOSTNAME;
    }

    public void start(){
        Socket client = null;
        try {
            client = new Socket(this.HOSTNAME,this.PORT);

            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
            DataInputStream inputStream = new DataInputStream(client.getInputStream());

            //recibo del servidor
            System.out.println(inputStream.readUTF());
            Thread.sleep(1000);

            //Envio un mensaje al cliente
            String mensaje = "hola soy el cliente";
            dataOutputStream.writeUTF(mensaje);

            Thread.sleep(3000);
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
