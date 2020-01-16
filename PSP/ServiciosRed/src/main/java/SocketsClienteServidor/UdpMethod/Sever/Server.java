package SocketsClienteServidor.UdpMethod.Sever;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    private int PORT;

    public Server(int PORT) {
        this.PORT = PORT;
    }

    public void connect(){
        try {
            DatagramSocket datagramSocket = new DatagramSocket(this.PORT);
        }catch (SocketException e){
            e.printStackTrace();
        }
    }
}
