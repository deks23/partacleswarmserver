package pl.damiankotynia.service;

import pl.damiankotynia.model.MVector;

public class RandomGenerator {

    public static long getRandom(long min, long max){
         return  (long)(Math.random()*((max-min)+1))+min;
    }

    public static double getRandom(double min, double max){
        return  (Math.random()*((max-min)+1))+min;
    }

    public static MVector getRandomVector(double min, double max){
        return new MVector(getRandom(min, max), getRandom(min, max));
    }
}
