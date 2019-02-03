package pl.damiankotynia.service;

import pl.damiankotynia.SwarmConstants;
import pl.damiankotynia.model.OptimizationTarget;
import pl.damiankotynia.model.Particle;
import pl.damiankotynia.model.Swarm;

public class SwarmValueChecker {
    public void checkValues(Swarm swarm) {
        if(swarm.getOptimizationTarget().equals(OptimizationTarget.MIN)){
            checkMin(swarm);
        }else{
            checkMax(swarm);
        }
    }

    private void checkMin(Swarm swarm){
        for(Particle particle : swarm.getSwarm()){
            if (particle.getCurrentValue().compareTo(swarm.getBestGlobalValue())<0){
                swarm.setBestGlobalPosition(particle.getBestLocalPosition());
                swarm.setBestGlobalValue(particle.getCurrentValue());
            }
            if(particle.getCurrentValue().compareTo(particle.getBestValue())<0){
                particle.setBestValue(particle.getCurrentValue());
                particle.setBestLocalPosition(particle.getPosition());
            }
        }
    }

    private void checkMax(Swarm swarm){
        for(Particle particle : swarm.getSwarm()){
            if (particle.getCurrentValue().compareTo(swarm.getBestGlobalValue())>0){
                swarm.setBestGlobalPosition(particle.getBestLocalPosition());
                swarm.setBestGlobalValue(particle.getCurrentValue());
            }
            if(particle.getCurrentValue().compareTo(particle.getBestValue())>0){
                particle.setBestValue(particle.getCurrentValue());
                particle.setBestLocalPosition(particle.getPosition());
            }
        }
    }
}
