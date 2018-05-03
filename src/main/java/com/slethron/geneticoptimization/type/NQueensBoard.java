package com.slethron.geneticoptimization.type;

import java.util.Objects;
import java.util.Random;

public class NQueensBoard {
    private int[] board;
    
    private NQueensBoard(int n) {
        board = new int[n];
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
    
    public void set(int column, int row) {
        board[column] = row;
    }
    
    public int length() {
        return board.length;
    }
    
    public static NQueensBoard generateRandomBoard(int n) {
        var random = new Random();
        var nQueensBoard = new NQueensBoard(n);
        for (var c = 0; c < nQueensBoard.length(); c++) {
            nQueensBoard.set(c, random.nextInt(nQueensBoard.length()));
        }
        
        return nQueensBoard;
    }
    
    public int numberOfConflicts() {
        var numberOfConflicts = 0;
        for (var currentQueen = 0; currentQueen < board.length - 1; currentQueen++) {
            for (var nextQueen = currentQueen; nextQueen < board.length; nextQueen++) {
                if (board[currentQueen] == board[nextQueen] ||
                        Math.abs(board[nextQueen] - board[currentQueen]) == Math.abs(nextQueen - currentQueen)) {
                    numberOfConflicts++;
                }
            }
        }

        return numberOfConflicts;
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
    
//    @Override
//    public String toString() {
//        var sb = new StringBuilder();
//        for (var column : board) {
//            for (var j = 0; j < board.length; j++) {
//                if (j == column) {
//                    sb.append("Q ");
//                } else {
//                    sb.append("* ");
//                }
//            }
//            sb.append('\n');
//        }
//
//        return sb.toString();
//    }
    
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("[");
        for (var column = 0; column < board.length; column++) {
            sb.append(board[column]);
            if (column != board.length - 1) {
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
}

