package ChatTCP.Servidor;

import java.io.IOException;

public class ServidorAplication {

    private final static int PORT = 9090;

    public static void main(String[] args) throws Exception {
        Servidor servidor = new Servidor(PORT);
        servidor.start();
    }
}
