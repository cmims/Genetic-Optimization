package com.slethron.evolution.population;

import com.slethron.evolution.individual.StringEvolvable;
import com.slethron.evolution.individual.interfaces.Evolvable;
import com.slethron.evolution.population.interfaces.Population;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
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
                if (m.fitness() == 0
                        && m instanceof StringEvolvable) {
                    return (StringEvolvable) m;
                }
            }
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
                .toArray(StringEvolvable[]::new);
    }
    
    @Override
    public StringEvolvable generateIndividualFromParents(StringEvolvable parentA, StringEvolvable parentB) {
        var child = new StringBuilder().append(parentA);
        var split = ThreadLocalRandom.current().nextInt(parentA.source().length());
        for (var i = split; i < parentB.source().length(); i++) {
            child.setCharAt(i, parentB.source().charAt(i));
        }
        
        return new StringEvolvable(child.toString());
    }
}
