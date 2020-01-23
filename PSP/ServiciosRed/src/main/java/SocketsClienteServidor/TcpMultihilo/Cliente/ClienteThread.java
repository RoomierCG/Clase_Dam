package SocketsClienteServidor.TcpMultihilo.Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClienteThread {

    public final int PORT;
    public final String HOSTNAME;
    private ExecutorService pool = Executors.newFixedThreadPool(10);

    public ClienteThread(int PORT, String HOSTNAME) {
        this.PORT = PORT;
        this.HOSTNAME = HOSTNAME;
    }

    public void start(){

        while (true){
            pool.submit(new Runnable() {
                public void run() {
                    int id = 0;
                    String HOSTNAME = ClienteThread.this.HOSTNAME;
                    int PORT = ClienteThread.this.PORT;

                    Socket client = null;
                    try {
                        client = new Socket(HOSTNAME,PORT);

                        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
                        DataInputStream inputStream = new DataInputStream(client.getInputStream());

                        //recibo del servidor
                        System.out.println(inputStream.readUTF());
                        Thread.sleep(1000);

                        //Envio un mensaje al cliente
                        String mensaje = "hola soy el cliente"+ Thread.currentThread().getId() + ".";
                        dataOutputStream.writeUTF(mensaje);
                        id++;

                        dataOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }

}
