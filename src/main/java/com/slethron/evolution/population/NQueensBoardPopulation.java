package com.slethron.evolution.population;

import com.slethron.evolution.individual.EvolvableNQueensBoard;
import com.slethron.evolution.population.interfaces.Population;
import com.slethron.evolution.type.NQueensBoard;

import java.util.Arrays;
import java.util.Random;

public class NQueensBoardPopulation implements Population<EvolvableNQueensBoard> {
    private EvolvableNQueensBoard[] population;
    private int boardLength;
    private Random random;
    
    public NQueensBoardPopulation(int populationSize, int boardLength) {
        this.boardLength = boardLength;
        population = generateInitialPopulation(populationSize);
        random = new Random();
    }
    
    public NQueensBoardPopulation(int populationSize) {
        this(populationSize, NQueensBoard.STANDARD_BOARD_LENGTH);
    }
    
    @Override
    public EvolvableNQueensBoard[] generateInitialPopulation(int populationSize) {
        var population = new EvolvableNQueensBoard[populationSize];
        for (var i = 0; i < population.length; i++) {
            population[i] = new EvolvableNQueensBoard(new NQueensBoard(boardLength));
        }
        
        return population;
    }
    
    /**
     * TODO: There is a problem with this method: the current way of generating a new
     * individual eventually causes all elements in the population to become essentially
     * the same. To avoid this, select some random individual from the top individual
     * instead of just choosing the next one.
     */
    @Override
    public EvolvableNQueensBoard[] nextGeneration() {
        random = new Random();
        Arrays.sort(population, new EvolvableNQueensBoard.EvolvableNQueensBoardComparator());
        var iParentA = random.nextInt(population.length / 4);
        var iParentB = random.nextInt(population.length / 4);
        
        var nextGeneration = new EvolvableNQueensBoard[population.length];
        for (int i = 0, j = 0; ; i++, j++) {
            nextGeneration[j] = generateIndividualFromParents(population[iParentA], population[iParentB]);
            if (i >= population.length / 4) {
                i = 0;
            }
            
            if (j == population.length - 1) {
                break;
            }
        }
        
        return nextGeneration;
    }
    
    @Override
    public EvolvableNQueensBoard generateIndividualFromParents(EvolvableNQueensBoard parentA,
                                                               EvolvableNQueensBoard parentB) {
        var child = new NQueensBoard(parentA.getSource().length());
        var split = random.nextInt(parentA.getSource().length());
        for (var i = 0; i < parentA.getSource().length(); i++) {
            if (i < split) {
                child.set(i, parentA.getSource().get(i));
            } else {
                child.set(i, parentB.getSource().get(i));
            }
        }
    
        return new EvolvableNQueensBoard(child);
    }
    
    @Override
    public EvolvableNQueensBoard[] getPopulation() {
        return population;
    }
    
    @Override
    public void setPopulation(EvolvableNQueensBoard[] population) {
        this.population = population;
    }
}
