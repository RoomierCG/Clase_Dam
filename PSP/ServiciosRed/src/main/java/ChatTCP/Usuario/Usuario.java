package ChatTCP.Usuario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Usuario{

    Scanner scanner = new Scanner(System.in);
    public int PORT;
    public String HOSTNAME, NameUser, mensaje;
    public Socket usuario;
    DataInputStream in;
    DataOutputStream out;

    public Usuario(String HOSTNAME, int PORT, String NameUser) {
        this.HOSTNAME = HOSTNAME;
        this.PORT = PORT;
        this.NameUser = NameUser;
    }

    public Usuario(Socket usuario) {
        this.usuario = usuario;
    }

    public void start() throws IOException {
        //Empezamos con la conexion al servidor
        usuario = new Socket(this.HOSTNAME,this.PORT);

        while(true){
            Runnable hilo = () -> {
                try {
                    mensaje = scanner.nextLine();

                    if(mensaje.startsWith("/end")){
                        out.writeUTF(NameUser+": Se ha ido de la conexion");
                        usuario.close();
                    }

                    out.writeUTF(mensaje);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
        }
    }

}
