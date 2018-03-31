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
        int order = 0;
        for (var i = 0; i < board.length; i++) {
            for (var j = 0; j < board.length; j++) {
                if (j != i && board[j] != -1 || board[j] == board[i] || board[j] == Math.abs(j - i)) {
                    order++;
                }
            }
            
        }
        return order;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int c : board) {
            sb.append(c);
            if (c != board.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        
        return sb.toString();
    }
}

