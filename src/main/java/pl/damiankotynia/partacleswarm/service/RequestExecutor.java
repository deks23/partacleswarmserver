package pl.damiankotynia.partacleswarm.service;

import org.mariuszgromada.math.mxparser.Function;
import pl.damiankotynia.model.*;
import pl.damiankotynia.partacleswarm.connector.OutboundConnection;
import pl.damiankotynia.partacleswarm.connector.ResponseSender;
import pl.damiankotynia.partacleswarm.database.Database;
import pl.damiankotynia.partacleswarm.exceptions.InvalidRequestFormatException;

import java.io.IOException;

import static pl.damiankotynia.model.OptimizationType.DIFFERENTIAL_EVOLUTION;
import static pl.damiankotynia.model.OptimizationType.PARTACLE_SWARM;


public class RequestExecutor {
    private ResponseSender responseSender;
    private OutboundConnection outboundConnection;

    public RequestExecutor(ResponseSender responseSender) {
        this.responseSender = responseSender;
        try {
            this.outboundConnection = new OutboundConnection(4443, "localhost");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public OptimizerResponse executeRequest(Object request) throws InvalidRequestFormatException {
        Request validatedRequest = getRequest(request);
        switch (validatedRequest.getOptimizationType()) {
            case DIFFERENTIAL_EVOLUTION:


                break;
            case PARTACLE_SWARM:
                Swarm swarm = new Swarm(validatedRequest.getParticleAmmount(), validatedRequest.getOptimizationTarget());
                ParticleMover particleMover = new ParticleMover(validatedRequest.getC1(),validatedRequest.getC2(), validatedRequest.getInteria());
                FunctionCalculator functionCalculator = new FunctionCalculator(validatedRequest.getFunction());
                SwarmValueChecker swarmValueChecker = new SwarmValueChecker();



                functionCalculator.calculate(swarm);
                swarmValueChecker.checkValues(swarm);

               // OutboundConnection outboundConnection = new OutboundConnection(4443, "localhost");
                //new Thread(outboundConnection).start();

                //swarm.getSwarm().stream().forEach(e -> System.out.println("BEFORE" + e));


                for(int a = 0; a<1000; a++){
                    functionCalculator.calculate(swarm);
                    swarmValueChecker.checkValues(swarm);
                    particleMover.moveParticles(swarm);
                    if(a%10==9) {

                        //outboundConnection.writeObject(swarm.getSwarm());
                    }
                }

                functionCalculator.calculate(swarm);
                swarmValueChecker.checkValues(swarm);


                swarm.getSwarm().stream().forEach(e -> System.out.println("AFTER: " + e));


                break;
                default:
                    throw new InvalidRequestFormatException();
        }

        return null;
    }


    private Request getRequest(Object request) throws InvalidRequestFormatException {
        if (!(request instanceof Request))
            throw new InvalidRequestFormatException();
        else
            return (Request) request;
    }




}
