package pl.damiankotynia.partacleswarm;


import pl.damiankotynia.partacleswarm.connector.OutboundConnection;
import pl.damiankotynia.model.OptimizationTarget;
import pl.damiankotynia.model.Point;
import pl.damiankotynia.model.Swarm;
import pl.damiankotynia.partacleswarm.service.FunctionCalculator;
import pl.damiankotynia.partacleswarm.service.ParticleMover;
import pl.damiankotynia.partacleswarm.service.SwarmValueChecker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {
        //int port = 4444;
        //new Thread(new Connector(port)).start();
        int i = 100;
        Swarm swarm = new Swarm(i, OptimizationTarget.MIN);
        ParticleMover particleMover = new ParticleMover(0.5,0.5, 0.5);
        FunctionCalculator functionCalculator = new FunctionCalculator("f(x, y) = (1 - x)^2 + 100 *  (y-x*x)^2");
        SwarmValueChecker swarmValueChecker = new SwarmValueChecker();
        functionCalculator.calculate(swarm);
        swarmValueChecker.checkValues(swarm);
        OutboundConnection outboundConnection = new OutboundConnection(4443, "localhost");

        outboundConnection.run();

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

/*
        Coord3d[] points = new Coord3d[i];
        Color[]   colors = new Color[i];
        int q =0;
        for(Particle particle : swarm.getSwarm()){
            points[q] = new Coord3d(particle.getPosition().getX(), particle.getPosition().getY(), 0.0);

        }*/


        List<Point> qwe  = new ArrayList<>(swarm.getSwarm());

        System.out.println(swarm.getBestGlobalValue() + " " + swarm.getBestGlobalPosition());

    }
}
