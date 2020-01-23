package SocketsClienteServidor.TcpMultihilo.Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {

    private final int PORT;
    private final String HOSTNAME;
    private ExecutorService pool = Executors.newFixedThreadPool(2);

    public Servidor(int PORT, String HOSTNAME) {
        this.PORT = PORT;
        this.HOSTNAME = HOSTNAME;
    }

    public void start() throws IOException {


        //Creamos un servidor Con un puerto y mediante la clase InetAddress cogeremos el localhost
            pool.submit(new Runnable() {
                public void run() {
                    ServerSocket serverSocket = null;
                    try {
                        serverSocket = new ServerSocket(9090);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Socket wait;
                    try{
                        while(true){
                            wait = serverSocket.accept();

                            DataInputStream dataInputStream = new DataInputStream(wait.getInputStream());
                            DataOutputStream dataOutputStream = new DataOutputStream(wait.getOutputStream());

                            //enviamos
                            dataOutputStream.writeUTF("hola cliente soy el servidor");

                            //leemos
                            System.out.println(dataInputStream.readUTF());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


    }

}
