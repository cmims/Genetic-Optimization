package com.slethron.geneticoptimization.problem;

import com.slethron.geneticoptimization.GeneticOptimizer;
import com.slethron.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public String mutate(String individual) {
        var index = random.nextInt(individual.length());
        var mutation = random.nextInt(individual.length());
        
        return new StringBuilder()
                .append(individual, 0, index)
                .append(Character.toChars(mutation))
                .append(individual,index + 1, individual.length())
                .toString();
    }
    
    @Override
    public double fitness(String individual) {
        var fitVal = 0;
        for (var i = 0; i < individual.length(); i++) {
            fitVal += Math.abs(target.charAt(i) - individual.charAt(i));
        }
        
        return fitVal;
    }
}
