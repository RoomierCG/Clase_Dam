package com.gmq.psp.sockets.chat.client;

import com.gmq.psp.sockets.chat.server.Server;
import com.gmq.psp.sockets.chat.server.ServerStatus;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import static com.gmq.psp.sockets.chat.server.Server.arrClientes;

public class Client {

    private String clientAdress, clientOutput, clientName;
    private int clientIp;
    private DataInputStream in;
    private DataOutputStream out;
    private Socket client;
    Runnable write;
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        //Con Este método enviamos al servidor un mensaje identificándolos y recibimos un mensaje suyo.
        wellcome();

        /*
            La clase ServerStatus contiene un Runnnable que sera el encargado de imprimir en la pantalla del user todos
            los inputs recibidos
         */
        ServerStatus serverStatus = new ServerStatus(client);
        new Thread(serverStatus).start();
        //Estará constantemente leyendo lo que el usuario meta por teclado
        mensages();
    }

    public void mensages() {
        write = () -> {
            try {
                out = new DataOutputStream(client.getOutputStream());
                while (true) {
                    clientOutput = scanner.nextLine();
                    out.writeUTF(this.clientName + ": " + clientOutput);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        write.run();
    }

    //Entrada al servidor
    public void wellcome() {
        try {
            client = new Socket(clientAdress, clientIp);
            in = new DataInputStream(client.getInputStream());
            System.out.println(in.readUTF());

            out = new DataOutputStream(client.getOutputStream());
            out.writeUTF("Gracias, soy " + this.clientName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Construtor y Getter del socket
    public Client(String clientAdress, int clientIp, String clientName) {
        this.clientAdress = clientAdress;
        this.clientIp = clientIp;
        this.clientName = clientName;
    }

    public Socket getClient() {
        return client;
    }
}
