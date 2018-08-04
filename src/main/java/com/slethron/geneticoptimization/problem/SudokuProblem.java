package com.slethron.geneticoptimization.problem;

import com.slethron.geneticoptimization.GeneticOptimizer;
import com.slethron.geneticoptimization.domain.SudokuBoard;
import com.slethron.geneticoptimization.util.NanoTimer;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuProblem implements GeneticOptimizer<SudokuBoard> {
    private Random random;
    
    public SudokuProblem() {
        random = new Random();
    }
    
    public List<SudokuBoard> generatePopulationFromIndividual(int populationSize, SudokuBoard individual) {
        return IntStream.range(0, populationSize)
                .parallel().unordered()
                .mapToObj(sudokuBoard -> mutate(individual, 1))
                .collect(Collectors.toList());
    }
    
    @Override
    public SudokuBoard generateIndividualFromParents(SudokuBoard parentA, SudokuBoard parentB) {
        var splitCol = random.nextInt(9);
        var splitRow = random.nextInt(9);
        var board = new int[9][9];
        for (var i = 0; i < 9; i++) {
            for (var j = 0; j < 9; j++) {
                if (i <= splitCol && j <= splitRow) {
                    board[i][j] = parentA.get(i, j);
                } else {
                    board[i][j] = parentB.get(i, j);
                }
            }
        }
        
        return new SudokuBoard(board, parentA.getStaticCells());
    }
    
    @Override
    public SudokuBoard mutate(SudokuBoard individual, double mutationRate) {
        var mutated = new SudokuBoard(individual);
        for (var i = 0; i < 9; i++) {
            for (var j = 0; j < 9; j++) {
                if (individual.getStaticCells().contains(new Pair<>(i, j))) {
                    continue;
                }
                if (random.nextDouble() <= mutationRate) {
                    var value = random.nextInt(9) + 1;
                    mutated.set(i, j, value);
                }
            }
        }
        
        return mutated;
    }
    
    @Override
    public double fitness(SudokuBoard individual) {
        var numberOfConflicts = 0;
        for (var column = 0; column < 9; column++) {
            for (var row = 0; row < 9; row++) {
                var num = individual.get(column, row);
                
                if (num == 0) break;
                
                for (var i = 0; i < 9; i++) {
                    if (i != column && individual.getBoard()[i][row] == num) {
                        numberOfConflicts++;
                    }
                    if (i != row && individual.getBoard()[column][i] == num) {
                        numberOfConflicts++;
                    }
                }
                
                int subGridOriginY = 0;
                int subGridOriginX = 0;
                if (column < 3 && row < 3) {
                    subGridOriginY = 0;
                    subGridOriginX = 0;
                } else if (column < 3 && row < 6) {
                    subGridOriginY = 0;
                    subGridOriginX = 3;
                } else if (column < 3 && row < 9) {
                    subGridOriginY = 0;
                    subGridOriginX = 6;
                } else if (column < 6 && row < 3) {
                    subGridOriginY = 3;
                    subGridOriginX = 0;
                } else if (column < 6 && row < 6) {
                    subGridOriginY = 3;
                    subGridOriginX = 3;
                } else if (column < 6 && row < 9) {
                    subGridOriginY = 3;
                    subGridOriginX = 6;
                } else if (column < 9 && row < 3) {
                    subGridOriginY = 6;
                    subGridOriginX = 0;
                } else if (column < 9 && row < 6) {
                    subGridOriginY = 6;
                    subGridOriginX = 3;
                } else if (column < 9 && row < 9) {
                    subGridOriginY = 6;
                    subGridOriginX = 6;
                }
                
                for (var i = subGridOriginY; i < subGridOriginY + 3; i++) {
                    for (var j = subGridOriginX; j < subGridOriginX + 3; j++) {
                        if (i != column && j != row && individual.getBoard()[i][j] == num) {
                            numberOfConflicts++;
                        }
                    }
                }
            }
        }
        
        return numberOfConflicts;
    }
    
    public static void main(String[] args) {
        var random = new Random();
        var board = new int[][] {
                { 4, 0, 0, 0, 0, 2, 8, 3, 0 },
                { 0, 8, 0, 1, 0, 4, 0, 0, 2 },
                { 7, 0, 6, 0, 8, 0, 5, 0, 0 },
                { 1, 0, 0, 0, 0, 7, 0, 5, 0 },
                { 2, 7, 0, 5, 0, 0, 0, 1, 9 },
                { 0, 3, 0, 9, 4, 0, 0, 0, 6 },
                { 0, 0, 8, 0, 9, 0, 7, 0, 5 },
                { 3, 0, 0, 8, 0, 6, 0, 9, 0 },
                { 0, 4, 2, 7, 0, 0, 0, 0, 3 }
        };
        
        var staticCells = new ArrayList<Pair<Integer, Integer>>() {{
            for (var i = 0; i < board.length; i++) {
                for (var j = 0; j < board.length; j++) {
                    if (board[i][j] != 0) {
                        add(new Pair<>(i, j));
                    }
                }
            }
        }};
        
        var sudokuBoard = new SudokuBoard(board, staticCells);
        System.out.println("Starting with board: ");
        System.out.println(sudokuBoard);
        
        for (var i = 0; i < board.length; i++) {
            for (var j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = random.nextInt(9) + 1;
                }
            }
        }
        
        sudokuBoard = new SudokuBoard(board, staticCells);
        var nanoTimer = new NanoTimer();
        var sudokuProblem = new SudokuProblem();
        
        nanoTimer.start();
        var population = sudokuProblem.generatePopulationFromIndividual(100, sudokuBoard);
        var solution = sudokuProblem.optimize(population, 1000, .05, .25);
        nanoTimer.stop();
        
        System.out.println();
        System.out.println("Solution for board: ");
        System.out.println(solution);
        System.out.println("Fitness of Solution: " + sudokuProblem.fitness(solution));
    }
}
