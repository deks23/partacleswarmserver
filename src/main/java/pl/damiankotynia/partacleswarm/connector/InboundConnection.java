package pl.damiankotynia.partacleswarm.connector;


import pl.damiankotynia.partacleswarm.exceptions.InvalidRequestFormatException;
import pl.damiankotynia.model.OptimizerResponse;
import pl.damiankotynia.partacleswarm.service.RequestExecutor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import static pl.damiankotynia.partacleswarm.service.Utils.INBOUND_CONNECTION_LOGGER;

public class InboundConnection implements Runnable {
    private Socket socket;
    private int clientNumber;
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;
    private RequestExecutor requestExecutor;
    private List<InboundConnection> connectionList;
    private boolean running;

    public InboundConnection(Socket socket, int client, List<InboundConnection> connectionList) {
        System.out.println(INBOUND_CONNECTION_LOGGER + "New InboundConnection");
        this.clientNumber = client;
        this.socket = socket;
        this.requestExecutor = new RequestExecutor();
        this.connectionList = connectionList;
        this.running = true;
        try {
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        while (running) {
            try {
                Object request = inputStream.readObject();
                System.out.println(INBOUND_CONNECTION_LOGGER + request.toString());
                OptimizerResponse response = null;
                try {
                    response = requestExecutor.executeRequest(request);
                } catch (InvalidRequestFormatException e) {
                    e.printStackTrace();
                }

        //TODO ustawienie na podstawie późniejszych stausów



            } catch (SocketException e) {
                System.out.println(INBOUND_CONNECTION_LOGGER + "Zerwano połączenie");
                running = !running;
                connectionList.remove(this);
            } catch (IOException e) {
                System.out.println(INBOUND_CONNECTION_LOGGER + "Zerwano połączenie2");
                running = !running;
                connectionList.remove(this);
            } catch (ClassNotFoundException e) {
                System.out.println(INBOUND_CONNECTION_LOGGER + "Niepoprawny format zapytania");
            }
        }


    }

    /**
     * Write string message on output stream
     * @param message message to send
     */
    public void sendMessage(String message) {
        try {
            synchronized (outputStream) {
                outputStream.writeObject(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
