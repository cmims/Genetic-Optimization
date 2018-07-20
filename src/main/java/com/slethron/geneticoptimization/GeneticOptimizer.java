package com.slethron.geneticoptimization;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public interface GeneticOptimizer<E> {
    
    default E solve(int populationSize, int generationLimit, double mutationRate, double fittestSampleRatio) {
        var population = generateInitialPopulation(populationSize);
        
        for (var generation = 0; generation < generationLimit; generation++) {
            var p = population;
            population = population.parallelStream()
                    .map(individual -> {
                        var sampleBound = (int) Math.round(populationSize * fittestSampleRatio);
                        var child = generateIndividualFromParents(
                                p.get(ThreadLocalRandom.current().nextInt(sampleBound)),
                                p.get(ThreadLocalRandom.current().nextInt(sampleBound)));
                        
                        child = mutate(child, mutationRate);
                        
                        return child;
                    }).sorted(Comparator.comparingDouble(this::fitness))
                    .collect(Collectors.toList());
            
            if (fitness(population.get(0)) == 0) {
                return population.get(0);
            }
        }
        
        return population.get(0);
    }
    
    List<E> generateInitialPopulation(int populationSize);
    
    E generateIndividualFromParents(E parentA, E parentB);
    
    E mutate(E individual, double mutationRate);
    
    double fitness(E individual);
}
