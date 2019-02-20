package pl.damiankotynia.partacleswarm.connector;

import pl.damiankotynia.model.ChartGeneratorResponse;
import pl.damiankotynia.model.OptimizerResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import static pl.damiankotynia.partacleswarm.service.Utils.INBOUND_CONNECTION_LOGGER;

public class ResponseListener implements Runnable {
    private ObjectInputStream inputStream;
    private boolean isRunning;
    private ResponseSender responseSender;

    public ResponseListener(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
        this.isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            BufferedImage responseObject = null;
            ChartGeneratorResponse response = null;

            try {

                response  = (ChartGeneratorResponse)inputStream.readObject();

                responseSender.sendResponse(response);

                System.out.println(INBOUND_CONNECTION_LOGGER + " recieved object ");

            } catch (IOException e) {
                System.out.println("\n Błąd serwera. Kończę działanie aplikacji \n");
                isRunning ^= true;
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("\n Niepoprawna odpowiedź serwera. Kończę działanie aplikacji \n");
                isRunning ^= true;
            }
        }
    }

    public void setResponseSender(ResponseSender responseSender) {
        this.responseSender = responseSender;
    }
}



