package pl.damiankotynia.partacleswarm.connector;

import pl.damiankotynia.model.Response;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ResponseListener implements Runnable {
    private ObjectInputStream inputStream;
    private boolean isRunning;

    public ResponseListener(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
        this.isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            Object responseObject = null;
            Response response = null;
            try {
                responseObject = inputStream.readObject();



            } catch (IOException e) {
                System.out.println("\n Błąd serwera. Kończę działanie aplikacji \n");
                isRunning ^= true;
            } catch (ClassNotFoundException e) {
                System.out.println("\n Niepoprawna odpowiedź serwera. Kończę działanie aplikacji \n");
                isRunning ^= true;
            }
        }
    }


}



