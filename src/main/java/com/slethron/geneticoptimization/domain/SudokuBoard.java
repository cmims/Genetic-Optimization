package com.slethron.geneticoptimization.domain;

import java.util.Arrays;

public class SudokuBoard implements Cloneable {
    public static final int EMPTY = 0;
    public static final int SIZE = 9;
    
    private int[][] board;
    private boolean[][] staticCells;
    
    public SudokuBoard() {
        board = new int[SIZE][SIZE];
        staticCells = new boolean[SIZE][SIZE];
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
    
    public boolean setStatic(int row, int column) {
        if (get(row, column) == EMPTY) {
            return false;
        }
        staticCells[row][column] = true;
        return true;
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
    
    @Override
    public SudokuBoard clone() {
        SudokuBoard clone = null;
        try {
            clone = (SudokuBoard) super.clone();
            clone.board = board.clone();
            clone.staticCells = staticCells.clone();
            for (var i = 0; i < SIZE; i++) {
                clone.board[i] = board[i].clone();
                clone.staticCells[i] = staticCells[i].clone();
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        
        return clone;
    }
}
