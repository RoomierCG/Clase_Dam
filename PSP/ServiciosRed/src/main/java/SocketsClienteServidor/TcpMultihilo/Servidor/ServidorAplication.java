package SocketsClienteServidor.TcpMultihilo.Servidor;

import java.io.IOException;

public class ServidorAplication {

    private final static int PORT = 9090;
    private final static String HOSTNAME = "localhost";

    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor(PORT,HOSTNAME);
        servidor.start();
    }
}
