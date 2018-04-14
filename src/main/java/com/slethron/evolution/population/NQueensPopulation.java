package com.slethron.evolution.population;

import com.slethron.evolution.individual.NQueensEvolvable;
import com.slethron.evolution.individual.interfaces.Evolvable;
import com.slethron.evolution.population.interfaces.Population;
import com.slethron.evolution.type.NQueensBoard;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class NQueensPopulation implements Population<NQueensEvolvable> {
    private NQueensEvolvable[] population;
    private int n;
    
    public NQueensPopulation(int populationSize, int n) {
        this.n = n;
        generateInitialPopulation(populationSize);
    }
    
    @Override
    public NQueensEvolvable evolve() {
        while (true) {
            for (NQueensEvolvable e : population) {
                if (e.fitness() == 0) {
                    return e;
                }
                
                Evolvable m = e.next();
                if (m.fitness() == 0
                        && m instanceof NQueensEvolvable) {
                    return (NQueensEvolvable) m;
                }
            }
        }
    }
    
    @Override
    public void generateInitialPopulation(int populationSize) {
        population = IntStream.range(0, populationSize)
                .parallel()
                .mapToObj(i -> new NQueensEvolvable(new NQueensBoard(n).randomize()))
                .toArray(NQueensEvolvable[]::new);
    }
    
    @Override
    public void nextGeneration() {
        Arrays.parallelSort(population, new NQueensEvolvable.NQueensEvolvableComparator());
        
        population = IntStream.range(0, population.length)
                .parallel()
                .mapToObj(i -> generateIndividualFromParents(
                        population[ThreadLocalRandom.current().nextInt(population.length / 4)],
                        population[ThreadLocalRandom.current().nextInt(population.length / 4)]))
                .toArray(NQueensEvolvable[]::new);
    }
    
    @Override
    public NQueensEvolvable generateIndividualFromParents(NQueensEvolvable parentA,
                                                          NQueensEvolvable parentB) {
        var child = new NQueensBoard(parentA.source());
        var split = ThreadLocalRandom.current().nextInt(parentA.source().length());
        for (var i = split; i < parentB.source().length(); i++) {
            child.set(i, parentB.source().get(i));
        }
        
        return new NQueensEvolvable(child);
    }
}
