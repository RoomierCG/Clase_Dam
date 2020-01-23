package SocketsClienteServidor.TcpMultihilo.Cliente;

public class ClienteAplication {

    private final static int PORT = 9090;
    private final static String HOSTNAME = "localhost";

    public static void main(String[] args) {

        Cliente cliente = new Cliente(PORT,HOSTNAME);
        cliente.start();
    }
}
