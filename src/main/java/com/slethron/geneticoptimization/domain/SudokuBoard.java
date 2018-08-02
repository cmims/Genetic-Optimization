package com.slethron.geneticoptimization.domain;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {
    private int[][] board;
    
    //List<Pair<column, row>>
    private List<Pair<Integer, Integer>> staticCells;
    
    public SudokuBoard(SudokuBoard source) {
        board = source.board.clone();
        staticCells = new ArrayList<>(source.staticCells);
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
        
        return true;
    }
}
