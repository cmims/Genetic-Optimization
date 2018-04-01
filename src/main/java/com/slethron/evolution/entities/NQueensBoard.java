package com.slethron.evolution.entities;

import java.util.Random;

public class NQueensBoard {
    private int[] board;
    
    public NQueensBoard(int n) {
        board = new int[n];
        if (n > 0) {
            randomize();
        }
    }
    
    public int get(int column) {
        return board[column];
    }
    
    public void set(int column, int row) {
        board[column] = row;
    }
    
    public int getLength() {
        return board.length;
    }
    
    private void randomize() {
        Random random = new Random();
        for (var c = 0; c < board.length; c++) {
            board[c] = random.nextInt(board.length);
        }
    }
    
    public int orderConflict() {
        int conflicts = 0;
        for(var currentQueen = 0; currentQueen < board.length; currentQueen++) {
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
}

