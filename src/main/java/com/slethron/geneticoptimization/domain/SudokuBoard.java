package com.slethron.geneticoptimization.domain;

import java.util.Arrays;

public class SudokuBoard {
    public static final int EMPTY = 0;
    public static final int SIZE = 9;
    
    private int[][] board;
    private boolean[][] staticCells;
    
    public SudokuBoard() {
        board = new int[SIZE][SIZE];
        staticCells = new boolean[SIZE][SIZE];
    }
    
    public SudokuBoard(SudokuBoard source) {
        board = new int[SIZE][SIZE];
        staticCells = new boolean[SIZE][SIZE];
        for (var row = 0; row < SIZE; row++) {
            for (var column = 0; column < SIZE; column++) {
                board[row][column] = source.board[row][column];
                staticCells[row][column] = source.staticCells[row][column];
            }
        }
    }
    
    public SudokuBoard(int[][] board, boolean[][] staticCells) {
        this.board = board;
        this.staticCells = staticCells;
    }
    
    public int get(int row, int column) {
        return board[row][column];
    }
    
    public boolean set(int row, int column, int value) {
        if (isStatic(row, column)) {
            return false;
        }
        board[row][column] = value;
        return true;
    }
    
    public int[][] getBoard() {
        return board;
    }
    
    public boolean[][] getStaticCells() {
        return staticCells;
    }
    
    public boolean isStatic(int row, int column) {
        return staticCells[row][column];
    }
    
    public void setStatic(int row, int column) {
        if (get(row, column) == 0) {
            throw new IllegalStateException("Empty cell cannot be static.");
        }
        staticCells[row][column] = true;
    }
    
    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (var row = 0; row < SIZE; row++) {
            if (row == 3 || row == 6) {
                builder.append("- - - + - - - + - - -\n");
            }
            for (var column = 0; column < SIZE; column++) {
                builder.append(get(row, column) == 0 ? "." : get(row, column));
                builder.append(" ");
                if (column == 2 || column == 5) {
                    builder.append("| ");
                }
            }
            builder.append('\n');
        }
        
        return builder.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        
        if (!(obj instanceof SudokuBoard)) {
            return false;
        }
        
        var e = (SudokuBoard) obj;
        
        for (var i = 0; i < SIZE; i++) {
            for (var j = 0; j < SIZE; j++) {
                if (e.get(i, j) != get(i, j)) {
                    return false;
                }
            }
        }
        
        for (var i = 0; i < SIZE; i++) {
            for (var j = 0; j < SIZE; j++) {
                if (e.isStatic(i, j) != isStatic(i, j)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }
}
