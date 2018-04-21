package com.slethron.evolution.population;

import com.slethron.evolution.individual.StringEvolvable;
import com.slethron.evolution.individual.interfaces.Evolvable;
import com.slethron.evolution.population.interfaces.Population;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringPopulation implements Population<StringEvolvable> {
    private StringEvolvable[] population;
    private String target;
    
    public StringPopulation(int populationSize, String target) {
        this.target = target;
        generateInitialPopulation(populationSize);
    }
    
    @Override
    public StringEvolvable evolve() {
        while (true) {
            for (StringEvolvable e : population) {
                if (e.fitness() == 0) {
                    return e;
                }
                
                Evolvable m = e.next();
                if (m.fitness() == 0) {
                    return (StringEvolvable) m;
                }
            }
            
            nextGeneration();
        }
    }
    
    @Override
    public void generateInitialPopulation(int populationSize) {
        population = IntStream.range(0, populationSize)
                .unordered().parallel()
                .mapToObj(i -> new StringEvolvable(target))
                .toArray(StringEvolvable[]::new);
    }
    
    @Override
    public void nextGeneration() {
        Arrays.parallelSort(population, new StringEvolvable.StringEvolvableComparator());
        
        population = IntStream.range(0, population.length)
                .unordered().parallel()
                .mapToObj(i -> generateIndividualFromParents(
                        population[ThreadLocalRandom.current().nextInt(population.length / 4)],
                        population[ThreadLocalRandom.current().nextInt(population.length / 4)]))
                .map(StringEvolvable::new)
                .toArray(StringEvolvable[]::new);
    }
    
    @Override
    public StringEvolvable generateIndividualFromParents(StringEvolvable parentA, StringEvolvable parentB) {
        var split = new AtomicInteger(ThreadLocalRandom.current().nextInt(parentA.source().length()));
        return new StringEvolvable(target,
                IntStream.range(0, parentA.source().length())
                        .unordered().parallel()
                        .mapToObj(i -> i < split.get() ? parentA.source().charAt(i) : parentB.source().charAt(i))
                        .map(String::valueOf)
                        .collect(Collectors.joining()));
    }
}
