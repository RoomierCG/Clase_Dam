package com.gmq.psp.sockets.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private int serverIp;
    private DataOutputStream out;
    private Socket socketClient;
    private String nameClient;
    public static List<Server> arrClientes = new ArrayList<>();

    public void start() {
        try {
            /*
            Iniciamos el servidor en un socketServer para que los usuarios se puedan conectar, tambien necesitaremos
            una pool de Hilos para que varios usuarios esten ejecutandose simultaneamente
             */
            ServerSocket server = new ServerSocket(serverIp);
            ExecutorService pool = Executors.newFixedThreadPool(5);

            /*
               Usaremos este while en este caso siempre esperar la entrada de algun usuario en cualquier momento con el
               server.accept().
               *nameClient: en esta variable almacenaremos el nombre de cliente gracias al metodo firstMsg
               *serverClient: este objeto lo creamos con el socket del usuario que se acaba de conectar junto con su
                              nombre anteriormente retornado.
               *arrClientes:  para podre manipular a todos los usuarios los guardaremos en esta lista, esta nos ayudara
                              a reenviar datos entre los usuarios.
               *pool.execute: Ahora crearemos un hilo dentro por cada usuarios que se conecte dentro de la pool, este
                              hilo es ThreadClient
             */
            while (true) {
                socketClient = server.accept();
                nameClient = firstMsg(socketClient);
                Server serverClient = new Server(this.socketClient, this.nameClient);

                arrClientes.add(serverClient);
                pool.execute(threadClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /*
        Este hilo se encarga de recoger el mensaje que envia un usuario de la pool, y dependiedo del tipo de mensaje
        el servidor reenviara al mensaje a los usuarios o usuario al que va destinado.
     */
    Runnable threadClient = () -> {
        try {
            DataInputStream in = new DataInputStream(socketClient.getInputStream());

            //Este bucle siempre buscando un output de algun usuario
            while (true) {
                /*
                    *clientOut: Para que el servidor sepa quien es el que ha enviado el mensaje recogeremos en esta
                                variable el nombre del usuario que se encuentra al principio del mensaje
                    *mensaje: recogemos el mensaje exceptuando quien lo a mandado,es decir, solo el texto
                 */
                try {
                    String mensaje = in.readUTF();
                    System.out.println("\t"+mensaje);

                    String clientOut = mensaje.substring(0, mensaje.indexOf(":"));
                    mensaje = mensaje.substring(mensaje.indexOf(" ") + 1);

                    //dependiendo de como sea el mensaje del usuario que lo ha enviado realizara "x" acciones
                    if (mensaje.startsWith("@")) {
                        privMsg(mensaje, clientOut);
                    } else {
                        globalMsg(mensaje, clientOut);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    //Este método se ejecutará solo cuando se conecte por primera vez un usuario
    private String firstMsg(Socket cliente) {
        try {
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            out.writeUTF("Bienvenido al chat");

            DataInputStream in = new DataInputStream(cliente.getInputStream());
            String NombreUsuario = in.readUTF();
            System.out.println(NombreUsuario);

            return NombreUsuario.substring(13);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //Methods
    public void privMsg(String mensaje, String clientOut) throws IOException {
        String clientIn = mensaje.substring(1, mensaje.indexOf(" "));

        /*
            Recorreremos la lista con todos los usuarios y si este no es el mismo usuario que lo ha enviado y en este
            caso si es el usuario al que le quiere enviar el mensaje se lo envia.
         */
        for (Server clientServer : arrClientes) {
            if (!clientServer.getNameClient().equals(clientOut) && clientServer.getNameClient().equals(clientIn)) {
                mensaje = mensaje.substring(mensaje.indexOf(" ") + 1);

                out = new DataOutputStream(clientServer.getSocketClient().getOutputStream());
                out.writeUTF("PRIVADO] " + clientOut + ": " + mensaje);
            }
        }
    }

    public void globalMsg(String mensaje, String clientOut) throws IOException {

        //recorre a todos los usuarios y se les manda el mensaje execep a quien lo ha escrito
        for (Server clientServer : arrClientes) {
            if (!clientServer.getNameClient().equals(clientOut)) {
                mensaje = mensaje.substring(mensaje.indexOf(" ") + 1);

                out = new DataOutputStream(clientServer.getSocketClient().getOutputStream());
                out.writeUTF(clientOut + ": " + mensaje);
            }
        }
    }

    //Constructores y Getter
    public Server(Socket socketClient, String nameClient) {
        this.socketClient = socketClient;
        this.nameClient = nameClient;
    }

    public Server(int serverIp) {
        this.serverIp = serverIp;
    }

    public Socket getSocketClient() {
        return socketClient;
    }

    public String getNameClient() {
        return nameClient;
    }
}
