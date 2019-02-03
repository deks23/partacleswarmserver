package pl.damiankotynia.model;

import pl.damiankotynia.service.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

import static pl.damiankotynia.SwarmConstants.*;

public class Swarm {
    private List<Particle> swarm;
    private MVector bestGlobalPosition;
    private double bestGlobalValue;
    private OptimizationTarget optimizationTarget;

    private Swarm(){

    }

    public Swarm(int particleAmmount, OptimizationTarget optimizationTarget) {
        swarm = new ArrayList<>(particleAmmount);
        this.optimizationTarget = optimizationTarget;
        if(optimizationTarget.equals(OptimizationTarget.MIN)){
            bestGlobalValue = Double.MAX_VALUE;
        }else {
            bestGlobalValue = Double.MIN_VALUE;
        }
        Particle particle = null;
        for (int i = 0; i < particleAmmount; i++) {
            particle = new Particle(RandomGenerator.getRandomVector(
                    MIN_POSITION, MAX_POSITION), RandomGenerator.getRandomVector(MIN_VELOCITY, MAX_VELOCITY), optimizationTarget);
            swarm.add(particle);
        }

    }

    public List<Particle> getSwarm() {
        return swarm;
    }

    public void setSwarm(List<Particle> swarm) {
        this.swarm = swarm;
    }

    public MVector getBestGlobalPosition() {
        return bestGlobalPosition;
    }

    public void setBestGlobalPosition(MVector bestGlobalPosition) {
        this.bestGlobalPosition = bestGlobalPosition;
    }

    public double getBestGlobalValue() {
        return bestGlobalValue;
    }

    public OptimizationTarget getOptimizationTarget() {
        return optimizationTarget;
    }

    public void setBestGlobalValue(double bestGlobalValue) {
        this.bestGlobalValue = bestGlobalValue;
    }
}
