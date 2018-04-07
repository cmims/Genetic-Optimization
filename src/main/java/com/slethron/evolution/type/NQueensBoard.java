package com.slethron.evolution.type;

import java.util.Objects;
import java.util.Random;

public class NQueensBoard {
    public static final int STANDARD_BOARD_LENGTH = 12;
    private int[] board;
    
    public NQueensBoard(int n) {
        board = new int[n];
        if (n > 0) {
            randomize();
        }
    }
    
    public NQueensBoard(NQueensBoard source) {
        board = source.board.clone();
    }
    
    public NQueensBoard(int[] board) {
        this.board = board;
    }
    
    public int get(int column) {
        return board[column];
    }
    
    public NQueensBoard set(int column, int row) {
        board[column] = row;
        
        return this;
    }
    
    public int length() {
        return board.length;
    }
    
    /**
     * TODO: Make sure the conditions in this method detect a NON-EVOLVABLE state
     * @return
     */
    public int orderConflict() {
        int conflicts = 0;
        for (var currentQueen = 0; currentQueen < board.length; currentQueen++) {
            for (var nextQueen = currentQueen + 1; nextQueen < board.length; nextQueen++) {
    
                // Queen Same Row
                if (board[currentQueen] == board[nextQueen]) {
                    conflicts++;
                }
    
                // Queen Diagonal
                // Their Difference In Row Equals Their Difference In Column
                // EX: Diagonal if two columns over and either two rows above or below
                if (Math.abs(board[nextQueen] - board[currentQueen]) == Math.abs(nextQueen - currentQueen)) {
                    conflicts++;
                }
            }
        }
        
        return conflicts;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        
        if (!(obj instanceof NQueensBoard)) {
            return false;
        }
        
        var e = (NQueensBoard) obj;
        
        if (e.length() != length()) {
            return false;
        }
        
        for (var i = 0; i < length(); i++) {
            if (e.get(i) != get(i)) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (var i = 0; i < board.length; i++) {
            sb.append(board[i]);
            if (i != board.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(board, board.length);
    }
    
    private void randomize() {
        var random = new Random();
        for (var c = 0; c < board.length; c++) {
            board[c] = random.nextInt(board.length);
        }
    }
}

