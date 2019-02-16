package pl.damiankotynia.connector;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Connector implements Runnable{
    private int port;
    private ServerSocket listener;
    private int clientNumber;
    private List<InboundConnection> connectionList;

    public Connector(int port) {
        this.port = port;
        clientNumber = 0;
        connectionList = new LinkedList<>();
        try {
            listener = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while(true){
            try {
                Socket socket = listener.accept();
                clientNumber++;
                InboundConnection connection = new InboundConnection(socket, clientNumber, connectionList);
                connectionList.add(connection);
                new Thread(connection).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
