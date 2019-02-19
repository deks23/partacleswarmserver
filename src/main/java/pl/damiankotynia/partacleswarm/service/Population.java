package pl.damiankotynia.partacleswarm.service;

import pl.damiankotynia.model.Point;

import java.util.List;

public class Population {
    private List<Point> initialPopulation;
    private List<Point> tempPopulation;
    private List<Point> afterCrossPopulation;

    public Population(List<Point> initialPopulation, List<Point> tempPopulation, List<Point> afterCrossPopulation) {
        this.initialPopulation = initialPopulation;
        this.tempPopulation = tempPopulation;
        this.afterCrossPopulation = afterCrossPopulation;
    }
}
