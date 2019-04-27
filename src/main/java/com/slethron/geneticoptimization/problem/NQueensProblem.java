package com.slethron.geneticoptimization.problem;

import com.slethron.geneticoptimization.DeterministicOptimizer;
import com.slethron.geneticoptimization.PopulationGenerator;
import com.slethron.geneticoptimization.domain.NQueensBoard;
import com.slethron.geneticoptimization.util.RandomGeneratorUtil;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NQueensProblem extends PopulationGenerator<NQueensBoard> implements DeterministicOptimizer<NQueensBoard> {
    private Random random;
    private int n;

    public NQueensProblem(int n) {
        random = new Random();
        this.n = n;
    }

    @Override
    public List<NQueensBoard> generateInitialPopulation(int populationSize) {
        return IntStream.range(0, populationSize)
                .parallel().unordered()
                .mapToObj(nQueensBoard -> RandomGeneratorUtil.generateRandomNQueensBoard(n))
                .collect(Collectors.toList());
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
                if (Math.abs(individual.get(nextQueen) - individual.get(currentQueen))
                        == Math.abs(nextQueen - currentQueen)) {
                    numberOfConflicts++;
                }
            }
        }

        return numberOfConflicts;
    }
}
