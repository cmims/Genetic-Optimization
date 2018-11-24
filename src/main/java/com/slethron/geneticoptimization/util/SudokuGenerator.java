package com.slethron.geneticoptimization.util;

import com.slethron.geneticoptimization.domain.SudokuBoard;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class SudokuGenerator {
    private static final int MIN_REQ_NUM_FILLED_CELLS = 20;
    private static final int MAX_NUM_RETRIES_TO_SOLVE = 50;
    
    private class SudokuFiller {
        private boolean fillRemainingEmptyCellsSequentially(SudokuBoard board) {
            for (var row = 0; row < SudokuBoard.SIZE; row++) {
                for (var column = 0; column < SudokuBoard.SIZE; column++) {
                    if (board.get(row, column) == 0) {
                        for (var num = 1; num <= SudokuBoard.SIZE; num++) {
                            if (isValidPlacement(board, num, row, column)) {
                                board.set(row, column, num);
                            
                                if (fillRemainingEmptyCellsSequentially(board)) {
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
        
        private boolean fillRemainingEmptyCellsRandomly(SudokuBoard board) {
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
                                if (fillRemainingEmptyCellsRandomly(board)) {
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
    public SudokuBoard generateRandomSolvableSudokuBoard(int numberOfFilledCells) {
        if (numberOfFilledCells < MIN_REQ_NUM_FILLED_CELLS) {
            throw new IllegalArgumentException("This generator can only generate boards with minimum "
                    + MIN_REQ_NUM_FILLED_CELLS + " filled cells.");
        }
        
        var random = new Random();
        var board = generateRandomSolvedSudokuBoard();
        
        var removedCount = 0;
        var noSolutionCount = 0;
        while (removedCount < SudokuBoard.SIZE * SudokuBoard.SIZE - numberOfFilledCells) {
            var row = random.nextInt(SudokuBoard.SIZE);
            var column = random.nextInt(SudokuBoard.SIZE);
            var removed = board.get(row, column);
            
            board.set(row, column, SudokuBoard.EMPTY);
            removedCount++;

            if (!isSolvable(board)) {
                board.set(row, column, removed);
                removedCount--;
                noSolutionCount++;
                if (noSolutionCount > MAX_NUM_RETRIES_TO_SOLVE) {
                    break;
                }
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

    private SudokuBoard generateRandomSolvedSudokuBoard() {
        var board = new SudokuBoard();
        var solver = new SudokuFiller();
        solver.fillRemainingEmptyCellsRandomly(board);
        return board;
    }
    
    private boolean isSolvable(SudokuBoard board) {
        var filler = new SudokuFiller();
        var tentativeSolution = new SudokuBoard(board);
        Integer firstEmptyCellRow = null;
        Integer firstEmptyCellColumn = null;
        outer:
        for (var row = 0; row < SudokuBoard.SIZE; row++) {
            for (var column = 0; column < SudokuBoard.SIZE; column++) {
                if (board.get(row, column) == SudokuBoard.EMPTY) {
                    firstEmptyCellRow = row;
                    firstEmptyCellColumn = column;
                    break outer;
                }
            }
        }

        if (Objects.isNull(firstEmptyCellRow)) {
            throw new IllegalArgumentException("Board has no remaining empty cells.");
        }

        if (!filler.fillRemainingEmptyCellsSequentially(tentativeSolution)) {
            return false;
        } else {
            var _board = new SudokuBoard(board);
            for (var lure = 1; lure <= SudokuBoard.SIZE; lure++) {
                if (lure == tentativeSolution.get(firstEmptyCellRow, firstEmptyCellColumn)) {
                    continue;
                }

                _board.set(firstEmptyCellRow, firstEmptyCellColumn, lure);
                if (filler.fillRemainingEmptyCellsSequentially(new SudokuBoard(_board))) {
                    return false;
                }
            }
        }

        return true;
    }
    
    public static void main(String[] args) {
        var sudokuGenerator = new SudokuGenerator();
        System.out.println(sudokuGenerator.generateRandomSolvableSudokuBoard(20));
    }
}
