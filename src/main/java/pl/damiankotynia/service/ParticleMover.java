package pl.damiankotynia.service;

import pl.damiankotynia.SwarmConstants;
import pl.damiankotynia.model.MVector;
import pl.damiankotynia.model.Particle;
import pl.damiankotynia.model.Swarm;

import java.util.Objects;


public class ParticleMover {


    private double c1;
    private double c2;


    public ParticleMover(double c1, double c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    public void moveParticles(Swarm swarm){
        MVector bestGlobal = swarm.getBestGlobalPosition();
        if(bestGlobal == null ){
            bestGlobal = SwarmConstants.ZERO_VECTOR;
        }
        for (Particle particle : swarm.getSwarm()){
            MVector bestLoc = particle.getBestLocalPosition();
            if(bestLoc == null ){
                bestLoc = SwarmConstants.ZERO_VECTOR;
            }
            particle.setVelocity(calculateNewVelocity(particle.getVelocity(), bestLoc, bestGlobal, particle.getPosition()));
            particle.setPosition(particle.getPosition().add(particle.getVelocity()));
        }
    }

    private MVector calculateNewVelocity (MVector oldVelocity, MVector bestLocal, MVector bestGlobal, MVector currentPosition){
       MVector temp =  bestGlobal.substractVector(currentPosition).multiplyVector(c2);
        return bestLocal.substractVector(currentPosition).multiplyVector(c1).add(temp).add(oldVelocity);
    }


}