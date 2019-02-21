package pl.damiankotynia.partacleswarm.service;

import pl.damiankotynia.model.*;
import pl.damiankotynia.partacleswarm.connector.OutboundConnection;
import pl.damiankotynia.partacleswarm.connector.ResponseSender;
import pl.damiankotynia.partacleswarm.exceptions.InvalidRequestFormatException;

import java.io.IOException;


public class RequestExecutor {
    private ResponseSender responseSender;
    private OutboundConnection outboundConnection;
    private int iterations = 50;
    public RequestExecutor(ResponseSender responseSender) {
        this.responseSender = responseSender;
        try {
            this.outboundConnection = new OutboundConnection(4443, "localhost");
            outboundConnection.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        outboundConnection.getResponseListener().setResponseSender(responseSender);
    }

    public OptimizerResponse executeRequest(Object request) throws InvalidRequestFormatException {
        Request validatedRequest = getRequest(request);
        iterations = validatedRequest.getIterations();
        switch (validatedRequest.getOptimizationType()) {
            case DIFFERENTIAL_EVOLUTION:
                DifferentialEvolution differentialEvolution = new DifferentialEvolution(validatedRequest.getParticleAmmount(),
                        validatedRequest.getC1(),
                        validatedRequest.getC2(),
                        validatedRequest.getFunction(),
                        validatedRequest.getOptimizationTarget());
                differentialEvolution.generatePopulation();
                for (int a = 0; a<iterations; a++ ){
                    differentialEvolution.mutate();
                    differentialEvolution.crossing();
                    differentialEvolution.selection();
                    differentialEvolution.clearTemp();
                    outboundConnection.writeObject(differentialEvolution.getInitialPopulation());
                }
                sendFinished();


                break;
            case PARTACLE_SWARM:
                Swarm swarm = new Swarm(validatedRequest.getParticleAmmount(), validatedRequest.getOptimizationTarget());
                ParticleMover particleMover = new ParticleMover(validatedRequest.getC1(), validatedRequest.getC2(), validatedRequest.getInteria());
                FunctionCalculator functionCalculator = new FunctionCalculator(validatedRequest.getFunction());
                SwarmValueChecker swarmValueChecker = new SwarmValueChecker();

                functionCalculator.calculate(swarm);
                swarmValueChecker.checkValues(swarm);

                for (int a = 0; a < iterations; a++) {
                    functionCalculator.calculate(swarm);
                    swarmValueChecker.checkValues(swarm);
                    particleMover.moveParticles(swarm);
                    outboundConnection.writeObject(swarm.getSwarm());
                }

                sendFinished();

                break;
            default:
                throw new InvalidRequestFormatException();
        }

        return null;
    }

    private void sendFinished() {
        Response response = new Response();
        response.setResponseType(ResponseType.FINISHED);

        response.setMessage("Zakonczono");


        try {
            responseSender.sendResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Request getRequest(Object request) throws InvalidRequestFormatException {
        if (!(request instanceof Request))
            throw new InvalidRequestFormatException();
        else
            return (Request) request;
    }


}
