package pl.damiankotynia.service;

import pl.damiankotynia.connector.Connector;
import pl.damiankotynia.database.Database;
import pl.damiankotynia.exceptions.InvalidRequestFormatException;
import pl.damiankotynia.model.Request;
import pl.damiankotynia.model.Response;
import pl.damiankotynia.model.ResponseType;

import static pl.damiankotynia.service.Utils.REQUEST_EXECUTOR_LOGGER;

public class RequestExecutor {

    private Database database;

    public RequestExecutor() {
        //this.database = Database.getInstance();
    }

    public Response executeRequest(Object request) throws InvalidRequestFormatException {

        return null;
    }




    private Request getRequest(Object request) throws InvalidRequestFormatException {
        if (!(request instanceof Request))
            throw new InvalidRequestFormatException();
        else
            return (Request) request;
    }




}
