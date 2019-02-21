package pl.damiankotynia.partacleswarm;


import pl.damiankotynia.partacleswarm.connector.Connector;
import java.io.IOException;



public class Main {

    public static void main(String[] args) throws IOException {
        int port = 4444;
        new Thread(new Connector(port)).start();
    }
}
