package pl.damiankotynia.partacleswarm.service;

import pl.damiankotynia.model.MVector;
import pl.damiankotynia.model.OptimizationTarget;
import pl.damiankotynia.model.Point;
import pl.damiankotynia.partacleswarm.service.RandomGenerator;

import java.text.DecimalFormat;
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
    private FunctionCalculator functionCalculator;
    private OptimizationTarget optimizationTarget;

    DecimalFormat df = new DecimalFormat("+#,##000.0000000000;-#");

    public DifferentialEvolution(int populationAmmount, double F, double Cr, String function, OptimizationTarget optimizationTarget) {
        this.populationAmmount = populationAmmount;
        this.Cr = Cr;
        this.F = F;
        this.initialPopulation = new ArrayList<>(populationAmmount);
        this.temporaryPopulation = new ArrayList<>(populationAmmount);
        this.afterCrossGeneration = new ArrayList<>(populationAmmount);
        this.functionCalculator = new FunctionCalculator(function);
        this.optimizationTarget = optimizationTarget;
    }

    public void generatePopulation(){
        for(int i = 0; i<populationAmmount; i++){
            initialPopulation.add(new Point(RandomGenerator.getRandomVector(MIN_POSITION, MAX_POSITION)));
        }
    }

    public void mutate(){
        for(int i = 0; i<initialPopulation.size(); i++){
            temporaryPopulation.add(mutatePoint(initialPopulation.get(i), i));
        }
    }

    public void crossing(){
        double random = RandomGenerator.getRandom(0, 1);
        for (int i = 0, j = 1; i < populationAmmount - 1; i += 2, j += 2) {
            if (random <= Cr) {
                crossTwoPoints(temporaryPopulation.get(i), temporaryPopulation.get(j));
            } else {
                afterCrossGeneration.add(temporaryPopulation.get(i));
                afterCrossGeneration.add(temporaryPopulation.get(j));
            }
        }

    }

    public void selection(){
        List<Point> tempList = new ArrayList<>();
        if(optimizationTarget.equals(OptimizationTarget.MAX)){
            for(int i = 0; i < populationAmmount; i++){
                if(functionCalculator.calculate(initialPopulation.get(i).getPosition())
                        > functionCalculator.calculate(afterCrossGeneration.get(i).getPosition())){
                    tempList.add(initialPopulation.get(i));
                }else {
                    tempList.add(afterCrossGeneration.get(i));
                }
            }
        }else{
            for(int i = 0; i < populationAmmount; i++){
                if(functionCalculator.calculate(initialPopulation.get(i).getPosition())
                        < functionCalculator.calculate(afterCrossGeneration.get(i).getPosition())){
                    tempList.add(initialPopulation.get(i));
                }else {
                    tempList.add(afterCrossGeneration.get(i));
                }
            }
        }
        initialPopulation = tempList;
    }

    private void crossTwoPoints(Point firstPoint, Point secondPoint) {
        Random random = new Random();
        int locus = random.nextInt(10) + 5;
        try {
            Double firstX = firstPoint.getPosition().getX() + secondPoint.getPosition().getX();
            Double secondX = secondPoint.getPosition().getX() + firstPoint.getPosition().getX();
            Double firstY = firstPoint.getPosition().getX() + secondPoint.getPosition().getX();
            Double secondY = secondPoint.getPosition().getX() + firstPoint.getPosition().getX();
            afterCrossGeneration.add(new Point(new MVector(firstX, firstY)));
            afterCrossGeneration.add(new Point(new MVector(Double.valueOf(secondX), Double.valueOf(secondY))));
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
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

    public List<Point> getInitialPopulation() {
        return initialPopulation;
    }
}
