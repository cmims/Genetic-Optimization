package com.slethron.geneticoptimization.domain;

import java.util.Arrays;

public class NQueensBoard implements Cloneable {
    private int[] board;
    
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
    
    public String drawAsciiBoard(char queen, char space) {
        var sb = new StringBuilder();
        for (var column : board) {
            for (var j = 0; j < board.length; j++) {
                if (j == column) {
                    sb.append(queen);
                } else {
                    sb.append(space);
                }
                sb.append(" ");
            }
            sb.append('\n');
        }
        
        return sb.toString();
    }
    
    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append("[");
        for (var column = 0; column < board.length; column++) {
            builder.append(board[column]);
            if (column != board.length - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        
        return builder.toString();
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
    public int hashCode() {
        return Arrays.hashCode(board);
    }

    @Override
    public NQueensBoard clone() {
        NQueensBoard clone = null;
        try {
            clone = (NQueensBoard) super.clone();
            clone.board = board.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }
}
