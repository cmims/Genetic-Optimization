package com.slethron.geneticoptimization.main;

import com.slethron.geneticoptimization.problem.SudokuProblem;
import com.slethron.geneticoptimization.util.RandomGeneratorUtil;

public class SudokuExample {
    public static void main(String[] args) {
        // 1. Generate unsolved board
        var board = RandomGeneratorUtil.generateRandomSudokuBoard(50);

        System.out.println("Unsolved board:");
        System.out.println(board);

        // 2. Solve the sudoku board with genetic optimization
        var sudokuProblem = new SudokuProblem(board);
        var initialPopulation = sudokuProblem.generateInitialPopulation(100);
        var solvedBoard = sudokuProblem.optimize(initialPopulation, .08, .25);

        System.out.println("Solved board:");
        System.out.println(solvedBoard);
    }
}
