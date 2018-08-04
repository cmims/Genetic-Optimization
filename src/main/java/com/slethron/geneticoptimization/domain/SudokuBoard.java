package com.slethron.geneticoptimization.domain;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

public class SudokuBoard {
    private int[][] board;
    private List<Pair<Integer, Integer>> staticCells;
    
    public SudokuBoard(SudokuBoard source) {
        board = source.board.clone();
        staticCells = source.staticCells;
    }
    
    public SudokuBoard(int[][] board, List<Pair<Integer, Integer>> staticCells) {
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
    
    public List<Pair<Integer, Integer>> getStaticCells() {
        return staticCells;
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
            for (var j = 0; j < board.length; j++) {
                if (e.get(i, j) != get(i, j)) {
                    return false;
                }
            }
        }
        
        for (var i = 0; i < staticCells.size(); i++) {
            if (staticCells.get(i).getKey().equals(e.staticCells.get(i).getKey())
                    && staticCells.get(i).getValue().equals(e.staticCells.get(i).getValue())) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }
}
