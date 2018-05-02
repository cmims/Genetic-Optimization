package com.slethron.geneticoptimization;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

public interface GeneticOptimizer<E> {
    
    default E solve(int populationSize, int generationLimit, double rateOfMutation) {
        var population = generateInitialPopulation(populationSize);
        population.sort(Comparator.comparingDouble(this::fitness));
        
        for (var generation = 0; generation < generationLimit; generation++) {
            var fitnessMappings = new ConcurrentHashMap<E,Double>();
            var p = population;
            
            population = IntStream.range(0, populationSize)
                    .unordered().parallel()
                    .mapToObj(i -> {
                        var child = generateIndividualFromParents(
                                p.get(ThreadLocalRandom.current().nextInt(populationSize / 4)),
                                p.get(ThreadLocalRandom.current().nextInt(populationSize / 4)));
                        
                        if (ThreadLocalRandom.current().nextInt(99) < ((rateOfMutation * 100) - 1)) {
                            child = mutate(child);
                        }
                        
                        return child;
                    })
                    .takeWhile(i -> {
                        Double fitVal = fitnessMappings.put(i, fitness(i));
                        
                        if (!isNull(fitVal) && fitVal == 0) {
                            p.set(0, i);
                            return false;
                        }
                        
                        return true;
                    }).sorted(Comparator.comparingDouble(fitnessMappings::get))
                    .collect(Collectors.toList());
            
            if (population.size() < populationSize) {
                population.set(0, p.get(0));
                break;
            }
        }
        
        return population.get(0);
    }
    
    List<E> generateInitialPopulation(int populationSize);
    
    E generateIndividualFromParents(E parentA, E parentB);
    
    E mutate(E individual);
    
    double fitness(E individual);
}
