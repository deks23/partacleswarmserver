package pl.damiankotynia.partacleswarm.service;


import pl.damiankotynia.partacleswarm.SwarmConstants;
import pl.damiankotynia.model.MVector;
import pl.damiankotynia.model.Particle;
import pl.damiankotynia.model.Swarm;

public class ParticleMover {


    private double c1;
    private double c2;
    private double interia;


    public ParticleMover(double c1, double c2, double interia){
        this.c1 = c1;
        this.c2 = c2;
        this.interia = interia;
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
      /* MVector temp =  bestGlobal.substractVector(currentPosition).multiplyVector(c2);
        return bestLocal.substractVector(currentPosition).multiplyVector(c1).add(temp).add(oldVelocity);*/

      MVector localFactor = bestLocal.substractVector(currentPosition).multiplyVector(c1*RandomGenerator.getRandom(0,1));
      MVector globalFactor = bestGlobal.substractVector(currentPosition).multiplyVector(c2*RandomGenerator.getRandom(0,1));
      MVector velocityFactor = oldVelocity.multiplyVector(interia);
      return velocityFactor.add(globalFactor).add(localFactor);
    }


}
