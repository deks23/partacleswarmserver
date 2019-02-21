package pl.damiankotynia.partacleswarm.service;

import pl.damiankotynia.model.MVector;
import pl.damiankotynia.model.Point;
import pl.damiankotynia.partacleswarm.service.RandomGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        for(int i = 0; i<initialPopulation.size(); i++){
            temporaryPopulation.add(mutatePoint(initialPopulation.get(i), i));
        }
    }

    public void crossing(){
        double random = RandomGenerator.getRandom(0, 1);
        if (random<=Cr){
            //mutant
        }else{
            //zostaje
        }

    }

    public void clearTemp(){
        temporaryPopulation.clear();
        afterCrossGeneration.clear();
    }

    private Point mutatePoint(Point point, int i){
        Random random = new Random();
        int firstIndex = random.nextInt(populationAmmount);
        int secondIndex = random.nextInt(populationAmmount);
        while (firstIndex == i) {
            firstIndex = random.nextInt(populationAmmount);
        }
        while (secondIndex == i || secondIndex == firstIndex) {
            secondIndex = random.nextInt(populationAmmount);
        }
        double x = point.getPosition().getX() + (F * (initialPopulation.get(firstIndex).getPosition().getX() - initialPopulation.get(secondIndex).getPosition().getX()));
        double y = point.getPosition().getY() + (F * (initialPopulation.get(firstIndex).getPosition().getY() - initialPopulation.get(secondIndex).getPosition().getY()));
        return new Point(new MVector(x, y));
    }
}
