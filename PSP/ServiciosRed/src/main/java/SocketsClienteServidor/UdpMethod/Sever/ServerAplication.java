package SocketsClienteServidor.UdpMethod.Sever;

public class ServerAplication {

    final static private int PORT = 9090;

    public static void main(String[] args) {
        Server server = new Server(PORT);
        server.connect();
    }
}
