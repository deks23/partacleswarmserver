package pl.damiankotynia.partacleswarm.service;

import pl.damiankotynia.model.Point;
import pl.damiankotynia.partacleswarm.service.RandomGenerator;
import java.util.ArrayList;
import java.util.List;
import static pl.damiankotynia.partacleswarm.SwarmConstants.*;

public class DifferentialEvolution {
    private List<Point> initialPopulation;
    private List<Point> temporaryPopulation;
    private List<Point> afterCrossGeneration;
    private double F;
    private double Cr;
    private int populationAmmount;

    public DifferentialEvolution(int populationAmmount, double F, double Cr) {
        this.populationAmmount = populationAmmount;
        this.Cr = Cr;
        this.F = F;
        this.initialPopulation = new ArrayList<>(populationAmmount);
    }

    public void generatePopulation(){
        for(int i = 0; i<populationAmmount; i++){
            initialPopulation.add(new Point(RandomGenerator.getRandomVector(MIN_POSITION, MAX_POSITION)));
        }
    }

    public void selectPoints(){
    }

    public void mutate(){

    }

    public void crossing(){

    }
}
