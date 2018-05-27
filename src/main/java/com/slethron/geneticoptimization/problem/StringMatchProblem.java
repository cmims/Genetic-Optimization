package com.slethron.geneticoptimization.problem;

import com.slethron.util.NanoTimer;
import com.slethron.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Objects.isNull;

public class StringMatchProblem implements GeneticOptimizer<String> {
    private Random random;
    private String target;
    
    public StringMatchProblem(String target) {
        random = new Random();
        this.target = target;
    }
    
    @Override
    public List<String> generateInitialPopulation(int populationSize) {
        var population = new ArrayList<String>();
        for (var i = 0; i < populationSize; i++) {
            population.add(RandomUtil.generateRandomString(target.length()));
        }
        
        return population;
    }
    
    @Override
    public String generateIndividualFromParents(String parentA, String parentB) {
        var split = random.nextInt(parentA.length());
        var child = new StringBuilder();
        for (var i = 0; i < parentA.length(); i++) {
            if (i <= split) {
                child.append(parentA.charAt(i));
            } else {
                child.append(parentB.charAt(i));
            }
        }
        
        return child.toString();
    }
    
    @Override
    public String mutate(String individual, double rateOfMutation) {
        var sb = new StringBuilder();
        for (var i = 0; i < individual.length(); i++) {
            if (random.nextDouble() <= rateOfMutation) {
                var mutation = random.nextInt(127 - 32) + 32;
                sb.append(Character.toChars(mutation));
            } else {
                sb.append(individual.charAt(i));
            }
        }
        
        return sb.toString();
    }
    
    @Override
    public double fitness(String individual) {
        var fitVal = 0;
        for (var i = 0; i < individual.length(); i++) {
            fitVal += Math.abs(target.charAt(i) - individual.charAt(i));
        }
        
        return fitVal;
    }
    
    public static void main(String[] args) {
        var nanoTimer = new NanoTimer();
        
        var target = "Hello, World!";
        var stringMatchProblem = new StringMatchProblem(target);
        
        nanoTimer.start();
        var solution = stringMatchProblem.solve(10000, 1000, .05, .25);
        nanoTimer.stop();
    
        System.out.println("Solution for target='" + target + "' found in " + nanoTimer.toString());
        System.out.println("Solution: " + solution);
    }
}
