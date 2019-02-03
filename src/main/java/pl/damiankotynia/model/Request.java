package pl.damiankotynia.model;

import org.mariuszgromada.math.mxparser.Function;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    private Function function;
    private OptimizationType optimizationType;
    private OptimizationTarget optimizationTarget;
    private int particleAmmount;


    public Request() {

    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public OptimizationType getOptimizationType() {
        return optimizationType;
    }

    public void setOptimizationType(OptimizationType optimizationType) {
        this.optimizationType = optimizationType;
    }

}
