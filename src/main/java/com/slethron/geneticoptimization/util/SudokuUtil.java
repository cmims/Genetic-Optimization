package com.slethron.geneticoptimization.util;

import com.slethron.geneticoptimization.domain.SudokuBoard;

import java.util.ArrayList;
import java.util.SplittableRandom;

class SudokuUtil {
    static SudokuBoard generateRandomSolvedSudokuBoard() {
        var board = new SudokuBoard();
        fillRemainingEmptyCellsRandomly(board);
        return board;
    }
    
    static boolean isSolvable(SudokuBoard board) {
        if (board.getEmptyCellCount() == 0) {
            throw new IllegalArgumentException("Board has no remaining empty cells.");
        }
        
        var tentativeSolution = new SudokuBoard(board);
        
        if (!fillRemainingEmptyCellsSequentially(tentativeSolution)) {
            return false;
        } else {
            if (board.getEmptyCellCount() == 1) {
                return true;
            }
            for (var row = 0; row < SudokuBoard.SIZE; row++) {
                for (var column = 0; column < SudokuBoard.SIZE; column++) {
                    if (board.get(row, column) == SudokuBoard.EMPTY) {
                        for (var num = 1; num <= SudokuBoard.SIZE; num++) {
                            if (num != tentativeSolution.get(row, column)
                                    && isValidPlacement(board, num, row, column)) {
                                var _board = new SudokuBoard(board);
                                _board.set(row, column, num);
                                if (fillRemainingEmptyCellsSequentially(_board)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return true;
    }
    
    private static boolean fillRemainingEmptyCellsSequentially(SudokuBoard board) {
        for (var row = 0; row < SudokuBoard.SIZE; row++) {
            for (var column = 0; column < SudokuBoard.SIZE; column++) {
                if (board.get(row, column) == SudokuBoard.EMPTY) {
                    for (var num = 1; num <= SudokuBoard.SIZE; num++) {
                        if (isValidPlacement(board, num, row, column)) {
                            board.set(row, column, num);
                            
                            if (fillRemainingEmptyCellsSequentially(board)) {
                                return true;
                            } else {
                                board.remove(row, column);
                            }
                        }
                    }
                    
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private static boolean fillRemainingEmptyCellsRandomly(SudokuBoard board) {
        var random = new SplittableRandom();
        
        for (var row = 0; row < SudokuBoard.SIZE; row++) {
            for (var column = 0; column < SudokuBoard.SIZE; column++) {
                if (board.get(row, column) == SudokuBoard.EMPTY) {
                    
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
                            if (fillRemainingEmptyCellsRandomly(board)) {
                                return true;
                            } else {
                                board.remove(row, column);
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
    
    private static boolean isValidPlacement(SudokuBoard board, int num, int row, int column) {
        for (var i = 0; i < SudokuBoard.SIZE; i++) {
            if (board.get(row, i) == num || board.get(i, column) == num) {
                return false;
            }
        }
        
        row = row - row % 3;
        column = column - column % 3;
        for (var i = row; i < row + 3; i++) {
            for (var j = column; j < column + 3; j++) {
                if (board.get(i, j) == num) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        var nanoTimer = new NanoTimer();
        var sudokuBoard = new SudokuBoard();
        sudokuBoard.set(0, 0, 1);
        
        System.out.println("Determine solvability of Sudoku Board:");
        System.out.println(sudokuBoard);
        
        nanoTimer.start();
        var solvable = isSolvable(sudokuBoard);
        nanoTimer.stop();
        System.out.println("Result: " + solvable + ", found in " + nanoTimer.toString());
    
        System.out.println("Generating new board: ...");
        nanoTimer.start();
        sudokuBoard = RandomGeneratorUtil.generateRandomSudokuBoard(25);
        nanoTimer.stop();
        System.out.println("Board generated in " + nanoTimer.toString());
        
        System.out.println("Determine solvability of Sudoku Board:");
        System.out.println(sudokuBoard);
        
        nanoTimer.start();
        solvable = isSolvable(sudokuBoard);
        nanoTimer.stop();
    
        System.out.println("Result: " + solvable + ", found in " + nanoTimer.toString());
    }
}
