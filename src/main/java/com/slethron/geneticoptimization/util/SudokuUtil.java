package com.slethron.geneticoptimization.util;

import com.slethron.geneticoptimization.domain.SudokuBoard;

import java.util.ArrayList;
import java.util.Objects;
import java.util.SplittableRandom;

public class SudokuUtil {
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
            Integer emptyCellRow = null;
            Integer emptyCellColumn = null;
            outer:
            for (var row = 0; row < SudokuBoard.SIZE; row++) {
                for (var column = 0; column < SudokuBoard.SIZE; column++) {
                    if (board.get(row, column) == SudokuBoard.EMPTY) {
                        var validCount = 0;
                        for (var num = 1; num <= SudokuBoard.SIZE; num++) {
                            if (isValidPlacement(board, num, column, row)) {
                                validCount++;
                            }
                        }
                        if (validCount > 1) {
                            emptyCellRow = row;
                            emptyCellColumn = column;
                            break outer;
                        }
                    }
                }
            }
            if (Objects.isNull(emptyCellRow)) {
                return true;
            }
            for (var lure = 1; lure <= SudokuBoard.SIZE; lure++) {
                var _board = new SudokuBoard(board);
                if (lure == tentativeSolution.get(emptyCellRow, emptyCellColumn)) {
                    continue;
                }
                
                if (isValidPlacement(board, lure, emptyCellRow, emptyCellColumn)) {
                    _board.set(emptyCellRow, emptyCellColumn, lure);
                    if (fillRemainingEmptyCellsSequentially(_board)) {
                        return false;
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
    
    private static boolean isValidPlacement(SudokuBoard board, int num, int row, int column) {
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
