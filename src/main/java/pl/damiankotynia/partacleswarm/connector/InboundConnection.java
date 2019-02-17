package pl.damiankotynia.partacleswarm.connector;


import pl.damiankotynia.partacleswarm.exceptions.InvalidRequestFormatException;
import pl.damiankotynia.model.Response;
import pl.damiankotynia.partacleswarm.service.RequestExecutor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import static pl.damiankotynia.partacleswarm.service.Utils.CONNECTION_LOGGER;

public class InboundConnection implements Runnable {
    private Socket socket;
    private int clientNumber;
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;
    private RequestExecutor requestExecutor;
    private List<InboundConnection> connectionList;
    private boolean running;

    public InboundConnection(Socket socket, int client, List<InboundConnection> connectionList) {
        System.out.println(CONNECTION_LOGGER + "New InboundConnection");
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
                System.out.println(CONNECTION_LOGGER + request.toString());
                Response response = null;
                try {
                    response = requestExecutor.executeRequest(request);
                } catch (InvalidRequestFormatException e) {
                    e.printStackTrace();
                }

        //TODO ustawienie na podstawie późniejszych stausów
       /*         switch (response.getResponseType()) {
                    case RESERVATION_COMPLETE:
                    case DELETED_RESERVATION:
                        brodcastMessage(response.getMessage());
                        synchronized (outputStream) {
                            outputStream.writeObject(response);
                        }
                        break;
                    case RESERVATION_FAILED:
                    case RESERVATION_REMOVING_FAILED:
                        synchronized (outputStream) {
                            outputStream.writeObject(response);
                        }
                        break;
                    case GET_OWN_RESERVATIONS:
                    case GET_RESERVATIONS:
                        synchronized (outputStream){
                            outputStream.writeObject(response);
                        }
                        break;
                }*/


            } catch (SocketException e) {
                System.out.println(CONNECTION_LOGGER + "Zerwano połączenie");
                running = !running;
                connectionList.remove(this);
            } catch (IOException e) {
                System.out.println(CONNECTION_LOGGER + "Zerwano połączenie2");
                running = !running;
                connectionList.remove(this);
            } catch (ClassNotFoundException e) {
                System.out.println(CONNECTION_LOGGER + "Niepoprawny format zapytania");
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
    /*TODO wywalić jak będzie zbędna
    private void brodcastMessage(String message) {
        for (InboundConnection connection : connectionList) {
            if (!this.equals(connection))
                connection.sendMessage(message);
        }
    }*/

}
