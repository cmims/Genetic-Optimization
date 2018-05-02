package com.slethron.geneticoptimization.problem;

import com.slethron.geneticoptimization.GeneticOptimizer;
import com.slethron.geneticoptimization.type.NQueensBoard;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NQueensProblem implements GeneticOptimizer<NQueensBoard> {
    private int n;
    
    public NQueensProblem(int n) {
        this.n = n;
    }
    
    @Override
    public List<NQueensBoard> generateInitialPopulation(int populationSize) {
        return IntStream.range(0, populationSize)
                .unordered().parallel()
                .mapToObj(i -> NQueensBoard.generateRandomBoard(n))
                .collect(Collectors.toList());
    }
    
    @Override
    public NQueensBoard generateIndividualFromParents(NQueensBoard parentA,
                                                      NQueensBoard parentB) {
        var split = new AtomicInteger(ThreadLocalRandom.current().nextInt(parentA.length()));
        return new NQueensBoard(new NQueensBoard(
                IntStream.range(0, parentA.length())
                        .unordered().parallel()
                        .map(i -> i < split.get() ? parentA.get(i) : parentB.get(i))
                        .toArray()));
    }
    
    @Override
    public NQueensBoard mutate(NQueensBoard individual) {
        var column = ThreadLocalRandom.current().nextInt(individual.length());
        var row = ThreadLocalRandom.current().nextInt(individual.length());
        
        var mutated = new NQueensBoard(individual);
        mutated.set(column, row);
        
        return mutated;
    }
    
    @Override
    public double fitness(NQueensBoard individual) {
        return individual.numberOfConflicts();
    }
}
