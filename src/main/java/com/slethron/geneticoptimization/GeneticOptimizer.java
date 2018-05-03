package com.slethron.geneticoptimization;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
public interface GeneticOptimizer<E> {
    
    default E solve(int populationSize, int generationLimit, double rateOfMutation) {
        var random = new Random();
        var population = generateInitialPopulation(populationSize);
        
        for (var generation = 0; generation < generationLimit; generation++) {
            population.sort(Comparator.comparingDouble(this::fitness));
    
            List<E> tempPopulation = new ArrayList<>();
            for (var i = 0; i < populationSize; i++) {
                var child = generateIndividualFromParents(population.get(random.nextInt(populationSize / 4)),
                        population.get(random.nextInt(populationSize / 4)));
                
                if (random.nextInt(99) < ((rateOfMutation * 100) - 1)) {
                    child = mutate(child);
                }
                
                if (fitness(child) == 0) {
                    return child;
                }
                
                tempPopulation.add(child);
            }
            
            population = tempPopulation;
        }

        return population.get(0);
    }
    
    List<E> generateInitialPopulation(int populationSize);
    
    E generateIndividualFromParents(E parentA, E parentB);
    
    E mutate(E individual);
    
    double fitness(E individual);
}
