package ChatTCP.Usuario;

import java.io.IOException;

public class UsuarioAplication {

    private final static int PORT = 9090;
    private final static String HOSTNAME = "127.0.0.1";
    private static String NombreUsuario = null;

    public static void main(String[] args) throws IOException {
        NombreUsuario = args[0];
        Usuario usuario = new Usuario(HOSTNAME,PORT,NombreUsuario);
        usuario.start();
    }
}
