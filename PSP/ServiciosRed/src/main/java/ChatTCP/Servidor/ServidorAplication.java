package ChatTCP.Servidor;

import java.io.IOException;
import java.net.Socket;

public class ServidorAplication {
    private final static int PORT = 9090;
    private final static String HOSTNAME = "localhost";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(HOSTNAME,PORT);
        Servidor servidor = new Servidor().start(socket);
    }
}
