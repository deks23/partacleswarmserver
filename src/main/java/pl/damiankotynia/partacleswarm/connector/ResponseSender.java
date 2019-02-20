package pl.damiankotynia.partacleswarm.connector;

import pl.damiankotynia.model.ChartGeneratorResponse;
import pl.damiankotynia.model.Response;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static pl.damiankotynia.partacleswarm.service.Utils.INBOUND_CONNECTION_LOGGER;

public class ResponseSender implements Runnable{

    private ObjectOutputStream outputStream;
    private boolean isRunning;

    public ResponseSender(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
        this.isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {


        }
    }

    public void sendResponse(Response response) throws IOException {
        try {
            outputStream.writeObject(response);
        } catch (IOException e) {
            System.out.println("\n Error while sending response. \n");
            isRunning ^= true;
            throw e;
        }
    }
}



