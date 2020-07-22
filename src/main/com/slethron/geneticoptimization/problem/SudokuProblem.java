package com.slethron.geneticoptimization.problem;

import com.slethron.geneticoptimization.DeterministicOptimizer;
import com.slethron.geneticoptimization.PopulationGenerator;
import com.slethron.geneticoptimization.domain.SudokuBoard;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuProblem extends PopulationGenerator<SudokuBoard> implements DeterministicOptimizer<SudokuBoard> {
    private Random random;
    private SudokuBoard unsolvedBoard;

    public SudokuProblem(SudokuBoard unsolvedBoard) {
        random = new Random();
        this.unsolvedBoard = unsolvedBoard;
    }

    @Override
    public List<SudokuBoard> generateInitialPopulation(int populationSize) {
        return IntStream.range(0, populationSize)
                .parallel().unordered()
                .mapToObj(sudokuBoard -> mutate(unsolvedBoard, 1))
                .collect(Collectors.toList());
    }

    @Override
    public SudokuBoard generateIndividualFromParents(SudokuBoard parentA, SudokuBoard parentB) {
        var splitRow = random.nextInt(SudokuBoard.SIZE);
        var splitColumn = random.nextInt(SudokuBoard.SIZE);
        var board = new int[SudokuBoard.SIZE][SudokuBoard.SIZE];
        for (var row = 0; row < SudokuBoard.SIZE; row++) {
            for (var column = 0; column < SudokuBoard.SIZE; column++) {
                if (row < splitRow || row == splitRow && column <= splitColumn) {
                    board[row][column] = parentA.get(row, column);
                } else {
                    board[row][column] = parentB.get(row, column);
                }
            }
        }

        return new SudokuBoard(board, parentA.getStaticCells());
    }

    @Override
    public SudokuBoard mutate(SudokuBoard individual, double mutationRate) {
        var mutated = new SudokuBoard(individual);
        for (var row = 0; row < SudokuBoard.SIZE; row++) {
            for (var column = 0; column < SudokuBoard.SIZE; column++) {
                if (individual.isStatic(row, column)) {
                    continue;
                }
                if (random.nextDouble() <= mutationRate) {
                    var value = random.nextInt(SudokuBoard.SIZE) + 1;
                    mutated.set(row, column, value);
                }
            }
        }

        return mutated;
    }

    @Override
    public double fitness(SudokuBoard individual) {
        var numberOfConflicts = 0;
        for (var row = 0; row < SudokuBoard.SIZE; row++) {
            for (var column = 0; column < SudokuBoard.SIZE; column++) {
                var num = individual.get(row, column);

                if (num == 0) break;

                for (var i = 0; i < SudokuBoard.SIZE; i++) {
                    if (i != row && individual.getBoard()[i][column] == num) {
                        numberOfConflicts++;
                    }
                    if (i != column && individual.getBoard()[row][i] == num) {
                        numberOfConflicts++;
                    }
                }

                var subGridOriginRow = (row / SudokuBoard.SUBGRID_SIZE) * SudokuBoard.SUBGRID_SIZE;
                var subGridOriginCol = (column / SudokuBoard.SUBGRID_SIZE) * SudokuBoard.SUBGRID_SIZE;

                for (var i = subGridOriginRow; i < subGridOriginRow + SudokuBoard.SUBGRID_SIZE; i++) {
                    for (var j = subGridOriginCol; j < subGridOriginCol + SudokuBoard.SUBGRID_SIZE; j++) {
                        if (!(i == row && j == column) && individual.get(i, j) == num) {
                            numberOfConflicts++;
                        }
                    }
                }
            }
        }

        return numberOfConflicts;
    }
}
