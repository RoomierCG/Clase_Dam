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

    //TODO que no puedan entrar mas de un user con el mismo nombre
    //todo que el usuario cierre su socket con consecuencia su hilo

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
                pool.execute(new Thread(threadClient));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /*
        Este hilo se encarga de recoger el mensaje que envia un usuario de la pool
     */
    Runnable threadClient = () -> {
        try {
            DataInputStream in = new DataInputStream(socketClient.getInputStream());
            while (true) {
                try {
                    String mensaje = in.readUTF();
                    System.out.println(mensaje);

                    String clientOut = mensaje.substring(0, mensaje.indexOf(":"));
                    mensaje = mensaje.substring(mensaje.indexOf(" ") + 1);

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

    private String firstMsg(Socket cliente) {
        try {
            DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
            out.writeUTF("Bienvenido al chat");

            DataInputStream in = new DataInputStream(cliente.getInputStream());
            String mensaje = in.readUTF();
            System.out.println(mensaje);
            return mensaje.substring(13);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //Methods
    public void privMsg(String mensaje, String clientOut) throws IOException {
        String clientIn = mensaje.substring(1, mensaje.indexOf(" "));

        for (Server clientServer : arrClientes) {
            if (!clientServer.getNameClient().equals(clientOut) && clientServer.getNameClient().equals(clientIn)) {
                mensaje = mensaje.substring(mensaje.indexOf(" ") + 1);

                out = new DataOutputStream(clientServer.getSocketClient().getOutputStream());
                out.writeUTF("PRIVADO] " + clientOut + ": " + mensaje);
            }
        }
    }

    public void globalMsg(String mensaje, String clientOut) throws IOException {

        for (Server clientServer : arrClientes) {
            if (!clientServer.getNameClient().equals(clientOut)) {
                mensaje = mensaje.substring(mensaje.indexOf(" ") + 1);

                out = new DataOutputStream(clientServer.getSocketClient().getOutputStream());
                out.writeUTF(clientOut + ": " + mensaje);
            }
        }
    }

    //Constructores y Getter
    public Server(int serverIp) {
        this.serverIp = serverIp;
    }

    public Server(Socket socketClient, String nameClient) {
        this.socketClient = socketClient;
        this.nameClient = nameClient;
    }

    public Socket getSocketClient() {
        return socketClient;
    }

    public String getNameClient() {
        return nameClient;
    }
}
