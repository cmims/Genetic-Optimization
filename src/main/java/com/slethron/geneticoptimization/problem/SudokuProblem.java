package com.slethron.geneticoptimization.problem;

import com.slethron.geneticoptimization.GeneticOptimizer;
import com.slethron.geneticoptimization.domain.SudokuBoard;

import java.util.List;
import java.util.Random;

public class SudokuProblem implements GeneticOptimizer<SudokuBoard> {
    private Random random;
    
    public SudokuProblem() {
        random = new Random();
    }
    
    @Override
    public List<SudokuBoard> generateInitialPopulation(int populationSize) {
        return null;
    }
    
    @Override
    public SudokuBoard generateIndividualFromParents(SudokuBoard parentA, SudokuBoard parentB) {
        return null;
    }
    
    @Override
    public SudokuBoard mutate(SudokuBoard individual, double mutationRate) {
        return null;
    }
    
    @Override
    public double fitness(SudokuBoard individual) {
        return 0;
    }
}
