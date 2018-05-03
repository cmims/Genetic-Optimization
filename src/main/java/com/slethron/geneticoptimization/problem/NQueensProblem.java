package com.slethron.geneticoptimization.problem;

import com.slethron.geneticoptimization.GeneticOptimizer;
import com.slethron.geneticoptimization.type.NQueensBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NQueensProblem implements GeneticOptimizer<NQueensBoard> {
    private Random random;
    private int n;
    
    public NQueensProblem(int n) {
        random = new Random();
        this.n = n;
    }
    
    @Override
    public List<NQueensBoard> generateInitialPopulation(int populationSize) {
        var population = new ArrayList<NQueensBoard>();
        for (var i = 0; i < populationSize; i++) {
            population.add(NQueensBoard.generateRandomBoard(n));
        }
        
        return population;
    }
    
    @Override
    public NQueensBoard generateIndividualFromParents(NQueensBoard parentA, NQueensBoard parentB) {
        var split = random.nextInt(parentA.length());
        var board = new int[parentA.length()];
        for (var i = 0; i < parentA.length(); i++) {
            if (i <= split) {
                board[i] = parentA.get(i);
            } else {
                board[i] = parentB.get(i);
            }
        }
        
        return new NQueensBoard(board);
    }
    
    @Override
    public NQueensBoard mutate(NQueensBoard individual) {
        var column = random.nextInt(individual.length());
        var row = random.nextInt(individual.length());
        
        var mutated = new NQueensBoard(individual);
        mutated.set(column, row);
        
        return mutated;
    }
    
    @Override
    public double fitness(NQueensBoard individual) {
        return individual.numberOfConflicts();
    }
}
