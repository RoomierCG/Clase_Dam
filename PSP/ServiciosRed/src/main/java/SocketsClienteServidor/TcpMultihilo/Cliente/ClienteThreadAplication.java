package SocketsClienteServidor.TcpMultihilo.Cliente;

public class ClienteThreadAplication {
    private final static int PORT = 9090;
    private final static String HOSTNAME = "localhost";

    public static void main(String[] args) {

        ClienteThread cliente = new ClienteThread(PORT,HOSTNAME);
        cliente.start();
    }
}
