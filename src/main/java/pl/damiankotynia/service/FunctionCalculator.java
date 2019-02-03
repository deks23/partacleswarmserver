package pl.damiankotynia.service;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;
import pl.damiankotynia.model.MVector;
import pl.damiankotynia.model.Particle;
import pl.damiankotynia.model.Swarm;


public class FunctionCalculator {
    private final Function function;
    private Expression expression;

    public FunctionCalculator(String function){
        this.function = new Function(function);
        this.expression = new Expression("", this.function);
    }

    public void calculate (Swarm swarm){
        for(Particle particle : swarm.getSwarm()){
            particle.setCurrentValue(calculate(particle.getPosition()));
        }
    }

    public double calculate(double x, double y){
        expression.setExpressionString(parseDoubleToExpresion(x, y));
        return expression.calculate();
    }

    public double calculate(MVector vector){
        return calculate(vector.getX(), vector.getY());
    }

    private String parseDoubleToExpresion(double x, double y){
        StringBuilder str = new StringBuilder();
        return str.append("f(").append(x).append(",").append(y).append(")").toString();
    }
}

