package pl.damiankotynia;

import org.jzy3d.colors.Color;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.Scatter;
import pl.damiankotynia.connector.Connection;
import pl.damiankotynia.connector.Connector;
import pl.damiankotynia.model.OptimizationTarget;
import pl.damiankotynia.model.Particle;
import pl.damiankotynia.model.Swarm;
import pl.damiankotynia.service.FunctionCalculator;
import pl.damiankotynia.service.ParticleMover;
import pl.damiankotynia.service.SwarmValueChecker;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws IOException {
        //int port = 4444;
        //new Thread(new Connector(port)).start();
        int i = 200;
        Swarm swarm = new Swarm(i, OptimizationTarget.MIN);
        ParticleMover particleMover = new ParticleMover(0.2,0.2);
        FunctionCalculator functionCalculator = new FunctionCalculator("f(x, y) = (1 - x)^2 + 100 *  (y-x*x)^2");
        SwarmValueChecker swarmValueChecker = new SwarmValueChecker();
        swarmValueChecker.checkValues(swarm);



        swarm.getSwarm().stream().forEach(e -> System.out.println("BEFORE" + e));


        for(int a = 0; a<100; a++){
            functionCalculator.calculate(swarm);
            swarmValueChecker.checkValues(swarm);
            particleMover.moveParticles(swarm);
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


        System.out.println(swarm.getBestGlobalValue() + " " + swarm.getBestGlobalPosition());

    }
}
