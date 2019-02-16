package pl.damiankotynia.connector;

import java.io.*;
import java.net.Socket;

public class OutboundConnection {

    private Socket socket;
    private int port;
    private String serverAddress;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private boolean isRunning;

    public OutboundConnection(int port, String serverAddress) throws IOException {
        socket = new Socket(serverAddress, port);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        this.isRunning = true;
    }

    public void run() {
        new Thread(new ResponseListener(inputStream)).start();
    }


    public synchronized boolean writeObject(Object object){
        try {
            outputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

