package com.slethron.geneticoptimization.problem;

import com.slethron.geneticoptimization.GeneticOptimizer;
import com.slethron.geneticoptimization.type.NQueensBoard;
import com.slethron.util.NanoTimer;
import com.slethron.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            population.add(RandomUtil.generateRandomNQueensBoard(n));
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
    public NQueensBoard mutate(NQueensBoard individual, double mutationRate) {
        var mutated = new NQueensBoard(individual);
        for (var column = 0; column < individual.length(); column++) {
            if (random.nextDouble() <= mutationRate) {
                var row = random.nextInt(individual.length());
                mutated.set(column, row);
            }
        }
        
        return mutated;
    }
    
    @Override
    public double fitness(NQueensBoard individual) {
        var numberOfConflicts = 0;
        for (var currentQueen = 0; currentQueen < individual.length() - 1; currentQueen++) {
            for (var nextQueen = currentQueen + 1; nextQueen < individual.length(); nextQueen++) {
                if (individual.get(currentQueen) == individual.get(nextQueen)) {
                    numberOfConflicts++;
                    continue;
                }
                if (Math.abs(individual.get(nextQueen) - individual.get(currentQueen)) == Math.abs(nextQueen - currentQueen)) {
                    numberOfConflicts++;
                }
            }
        }
        
        return numberOfConflicts;
    }
    
    public static void main(String[] args) {
        var nanoTimer = new NanoTimer();
        
        var n = 24;
        var nQueensProblem = new NQueensProblem(n);
        
        nanoTimer.start();
        var solution = nQueensProblem.solve(10000, 1000, .05, .25);
        nanoTimer.stop();
    
        System.out.println("Solution for n=" + n + " found in " + nanoTimer.toString());
        System.out.println(solution);
    }
}
