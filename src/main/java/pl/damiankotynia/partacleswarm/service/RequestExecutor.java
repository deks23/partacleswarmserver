package pl.damiankotynia.partacleswarm.service;

import pl.damiankotynia.partacleswarm.database.Database;
import pl.damiankotynia.partacleswarm.exceptions.InvalidRequestFormatException;
import pl.damiankotynia.model.Request;
import pl.damiankotynia.model.OptimizerResponse;


public class RequestExecutor {

    private Database database;

    public RequestExecutor() {
        //this.database = Database.getInstance();
    }

    public OptimizerResponse executeRequest(Object request) throws InvalidRequestFormatException {

        return null;
    }




    private Request getRequest(Object request) throws InvalidRequestFormatException {
        if (!(request instanceof Request))
            throw new InvalidRequestFormatException();
        else
            return (Request) request;
    }




}
