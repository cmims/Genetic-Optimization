package com.slethron.geneticoptimization;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public interface GeneticOptimizer<E> {
    
    default E solve(int populationSize, int generationLimit, double mutationRate, double fittestSampleRatio) {
        var random = new Random();
        var population = generateInitialPopulation(populationSize);
        
        for (var generation = 0; generation < generationLimit; generation++) {
            population.sort(Comparator.comparingDouble(this::fitness));
    
            if (fitness(population.get(0)) == 0) {
                return population.get(0);
            }
            
            var nextGeneration = new ArrayList<E>();
            for (var individual = 0; individual < populationSize; individual++) {
                Long sampleBound = Math.round(populationSize * fittestSampleRatio);
                var child = generateIndividualFromParents(
                        population.get(random.nextInt(sampleBound.intValue())),
                        population.get(random.nextInt(sampleBound.intValue()))
                );
                
                child = mutate(child, mutationRate);
                
                nextGeneration.add(child);
            }
            
            population = nextGeneration;
        }
        
        return population.get(0);
    }
    
    List<E> generateInitialPopulation(int populationSize);
    
    E generateIndividualFromParents(E parentA, E parentB);
    
    E mutate(E individual, double mutationRate);
    
    double fitness(E individual);
}
