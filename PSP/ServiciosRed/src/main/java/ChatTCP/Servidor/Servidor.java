package ChatTCP.Servidor;

import ChatTCP.Usuario.Usuario;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor{

    private final int PORT;
    private ExecutorService threadPool = Executors.newFixedThreadPool(4);

    public Servidor(int PORT) {
        this.PORT = PORT;
    }

    public void start() throws Exception {
        //Creamos el socket del servidor

        ServerSocket serverSocket = new ServerSocket(PORT);
        while(true){
            System.out.println("Server: Esperando Conexiones");
            Socket Usuario = serverSocket.accept();

            System.out.println("Server: Conexion aceptada");
            Usuario usuario = new Usuario(Usuario);
            usuario.start();
        }
    }
}
