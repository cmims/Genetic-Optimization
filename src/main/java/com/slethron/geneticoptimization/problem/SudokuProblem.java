package com.slethron.geneticoptimization.problem;

import com.slethron.geneticoptimization.GeneticOptimizer;
import com.slethron.geneticoptimization.domain.SudokuBoard;
import com.slethron.geneticoptimization.util.NanoTimer;
import com.slethron.geneticoptimization.util.SudokuBoardUtil;

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
                if (individual.isStatic(i, j)) {
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
                
                int subGridOriginY;
                int subGridOriginX;
                if (column < 3 && row < 3) {
                    subGridOriginY = 0;
                    subGridOriginX = 0;
                } else if (column < 3 && row < 6) {
                    subGridOriginY = 0;
                    subGridOriginX = 3;
                } else if (column < 3) {
                    subGridOriginY = 0;
                    subGridOriginX = 6;
                } else if (column < 6 && row < 3) {
                    subGridOriginY = 3;
                    subGridOriginX = 0;
                } else if (column < 6 && row < 6) {
                    subGridOriginY = 3;
                    subGridOriginX = 3;
                } else if (column < 6) {
                    subGridOriginY = 3;
                    subGridOriginX = 6;
                } else if (row < 3) {
                    subGridOriginY = 6;
                    subGridOriginX = 0;
                } else if (row < 6) {
                    subGridOriginY = 6;
                    subGridOriginX = 3;
                } else {
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
//        var random = new Random();
        var sudokuBoard = SudokuBoardUtil.getUnsolvedBoardX();
//        var board = new int[9][9];
//        var staticCells = new ArrayList<Pair<Integer, Integer>>();
//        var sudokuBoard = new SudokuBoard(board, staticCells);
//        for (var i = 0; i < 9; i++) {
//            for (var j = 0; j < 9; j++) {
//                if (sudokuBoard.get(i, j) == 0) {
//                    sudokuBoard.set(i, j, random.nextInt(9) + 1);
//                }
//            }
//        }
    
        System.out.println("Starting with board: ");
        System.out.println(sudokuBoard);
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
