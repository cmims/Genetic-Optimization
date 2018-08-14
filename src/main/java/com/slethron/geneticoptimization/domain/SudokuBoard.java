package com.slethron.geneticoptimization.domain;

import java.util.Arrays;

public class SudokuBoard {
    private int[][] board;
    private boolean[][] staticCells;
    
    public SudokuBoard(SudokuBoard source) {
        board = source.board.clone();
        staticCells = source.staticCells.clone();
    }
    
    public SudokuBoard(int[][] board, boolean[][] staticCells) {
        this.board = board;
        this.staticCells = staticCells;
    }
    
    public int get(int column, int row) {
        return board[column][row];
    }
    
    public void set(int column, int row, int value) {
        board[column][row] = value;
    }
    
    public int[][] getBoard() {
        return board;
    }
    
    public boolean[][] getStaticCells() {
        return staticCells;
    }
    
    public boolean isStatic(int column, int row) {
        return staticCells[column][row];
    }
    
    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (var column = 0; column < board.length; column++) {
            if (column == 3 || column == 6) {
                builder.append("- - - + - - - + - - -\n");
            }
            for (var row = 0; row < board.length; row++) {
                builder.append(get(column, row) == 0 ? "." : get(column, row));
                builder.append(" ");
                if (row == 2 || row == 5) {
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
        
        for (var i = 0; i < board.length; i++) {
            for (var j = 0; j < board[i].length; j++) {
                if (e.get(i, j) != get(i, j)) {
                    return false;
                }
            }
        }
        
        for (var i = 0; i < staticCells.length; i++) {
            for (var j = 0; j < staticCells[i].length; j++) {
                if (isStatic(i, j) != e.isStatic(i, j)) {
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
