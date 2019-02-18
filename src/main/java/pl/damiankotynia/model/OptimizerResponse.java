package pl.damiankotynia.model;

import java.io.Serializable;

public class OptimizerResponse extends Response implements Serializable {
    private String message;

    //TODO dopisac format responsa z optimizera
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
