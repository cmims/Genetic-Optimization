package com.slethron.geneticoptimization.util;

import com.slethron.geneticoptimization.domain.SudokuBoard;

import java.util.ArrayList;
import java.util.Random;

public class SudokuGenerator {
    private class SudokuSolver {
        private boolean solve(SudokuBoard board) {
            for (var row = 0; row < SudokuBoard.SIZE; row++) {
                for (var column = 0; column < SudokuBoard.SIZE; column++) {
                    if (board.get(row, column) == 0) {
                        for (var num = 1; num <= SudokuBoard.SIZE; num++) {
                            if (isValidPlacement(board, num, row, column)) {
                                board.set(row, column, num);
                            
                                if (solve(board)) {
                                    return true;
                                } else {
                                    board.set(row, column, SudokuBoard.EMPTY);
                                }
                            }
                        }
                    
                        return false;
                    }
                }
            }
        
            return true;
        }
        
        private boolean solveRandomly(SudokuBoard board) {
            var random = new Random();
        
            for (var row = 0; row < SudokuBoard.SIZE; row++) {
                for (var column = 0; column < SudokuBoard.SIZE; column++) {
                    if (board.get(row, column) == 0) {
                    
                        var unattempted = new ArrayList<Integer>() {{
                            for (var i = 0; i <= SudokuBoard.SIZE; i++) {
                                add(i);
                            }
                        }};
                        while (true) {
                            var attempt = unattempted.get(random.nextInt(unattempted.size()));
                            unattempted.remove(attempt);
                        
                            if (isValidPlacement(board, attempt, row, column)) {
                                board.set(row, column, attempt);
                                if (solveRandomly(board)) {
                                    return true;
                                } else {
                                    board.set(row, column, SudokuBoard.EMPTY);
                                }
                            }
                            if (unattempted.isEmpty()) {
                                return false;
                            }
                        }
                    }
                }
            }
        
            return true;
        }
    
        private boolean isValidPlacement(SudokuBoard board, int num, int row, int column) {
            for (var i = 0; i < SudokuBoard.SIZE; i++) {
                if (board.get(row, i) == num) {
                    return false;
                }
            }
        
            for (var i = 0; i < SudokuBoard.SIZE; i++) {
                if (board.get(i, column) == num) {
                    return false;
                }
            }
        
            var r = row - row % 3;
            var c = column - column % 3;
            for (var i = r; i < r + 3; i++) {
                for (var j = c; j < c + 3; j++) {
                    if (board.get(i, j) == num) {
                        return false;
                    }
                }
            }
        
            return true;
        }
    }
    
    /**
     * Generates a Sudoku board object with many of the elements removed so that it is in an unsolved state. The board
     * is solvable.
     *
     * @return The generated random Sudoku board
     */
    public SudokuBoard generateRandomSolvableSudokuBoard() {
        var random = new Random();
        var board = generateRandomSolvedSudokuBoard();
        
        var removedCount = 0;
        while (removedCount < 81 - 16) {
            var row = random.nextInt(SudokuBoard.SIZE);
            var column = random.nextInt(SudokuBoard.SIZE);
            var removed = board.get(row, column);
            
            if (board.isStatic(row, column)) {
                continue;
            }
            
            board.set(row, column, SudokuBoard.EMPTY);
            removedCount++;

            var isSolvable = isSolvable(board);
            if (!isSolvable) {
                board.set(row, column, removed);
                break;
            }
        }
        
        for (var row = 0; row < SudokuBoard.SIZE; row++) {
            for (var column = 0; column < SudokuBoard.SIZE; column++) {
                if (board.get(row, column) != SudokuBoard.EMPTY) {
                    board.setStatic(row, column);
                }
            }
        }
        
        return board;
    }
    
    /**
     * If attempting to generate a solved random Sudoku board by starting at cell [0, 0] and inserting random numbers
     * that are not in conflict with the previously inserted numbers, it is not possible to reproducibly insert numbers
     * so that the board remains in a solvable state after each number placement without a backtracking algorithm.
     *
     * The goal is to perform the same function as in the original idea, but when running into the condition that the
     * list of numbers not yet been used in a placement attempt have all been used in a placement attempt
     * unsuccessfully, remove the previous number and try again, this time removing that number from the list of
     * possibilities for placement attempt.
     *
     * @return The solved Sudoku board
     */
    private SudokuBoard generateRandomSolvedSudokuBoard() {
        var board = new SudokuBoard();
        var solver = new SudokuSolver();
        solver.solveRandomly(board);
        return board;
    }
    
    private boolean isSolvable(SudokuBoard board) {
        return new SudokuSolver().solve(board.clone());
    }
    
    public static void main(String[] args) {
        var sudokuGenerator = new SudokuGenerator();
        System.out.println(sudokuGenerator.generateRandomSolvableSudokuBoard());
    }
}
