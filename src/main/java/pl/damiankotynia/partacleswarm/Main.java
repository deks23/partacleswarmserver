package pl.damiankotynia.partacleswarm;


import org.mariuszgromada.math.mxparser.Function;
import pl.damiankotynia.partacleswarm.connector.OutboundConnection;
import pl.damiankotynia.model.OptimizationTarget;
import pl.damiankotynia.model.Point;
import pl.damiankotynia.model.Swarm;
import pl.damiankotynia.partacleswarm.service.FunctionCalculator;
import pl.damiankotynia.partacleswarm.service.ParticleMover;
import pl.damiankotynia.partacleswarm.service.SwarmValueChecker;
import pl.damiankotynia.partacleswarm.connector.Connector;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {
        int port = 4444;
        new Thread(new Connector(port)).start();
     /*   int i = 100;
        Swarm swarm = new Swarm(i, OptimizationTarget.MAX);
        ParticleMover particleMover = new ParticleMover(0.5,0.5, 0.5);
        FunctionCalculator functionCalculator = new FunctionCalculator("f(x, y) = (1 - x)^2 + 100 *  (y-x*x)^2");
        SwarmValueChecker swarmValueChecker = new SwarmValueChecker();




        functionCalculator.calculate(swarm);
        swarmValueChecker.checkValues(swarm);

        OutboundConnection outboundConnection = new OutboundConnection(4443, "localhost");
        new Thread(outboundConnection).start();

        //swarm.getSwarm().stream().forEach(e -> System.out.println("BEFORE" + e));


        for(int a = 0; a<1000; a++){
            functionCalculator.calculate(swarm);
            swarmValueChecker.checkValues(swarm);
            particleMover.moveParticles(swarm);
            if(a%10==9) {

                outboundConnection.writeObject(swarm.getSwarm());
            }
        }

        functionCalculator.calculate(swarm);
        swarmValueChecker.checkValues(swarm);


        swarm.getSwarm().stream().forEach(e -> System.out.println("AFTER: " + e));



        List<Point> qwe  = new ArrayList<>(swarm.getSwarm());

        System.out.println(swarm.getBestGlobalValue() + " " + swarm.getBestGlobalPosition());*/

    }
}
