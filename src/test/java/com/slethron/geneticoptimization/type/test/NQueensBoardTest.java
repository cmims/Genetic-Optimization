package com.slethron.geneticoptimization.type.test;

import com.slethron.geneticoptimization.type.NQueensBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NQueensBoardTest {
    private int[] board;
    private NQueensBoard nQueensBoard;
    
    @BeforeEach
    void beforeEach() {
        board = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        nQueensBoard = new NQueensBoard(board);
    }
    
    @Test
    void getReturnsRowForQueenInColumn() {
        for (var column = 0; column < nQueensBoard.length(); column++) {
            assertEquals(board[column], nQueensBoard.get(column));
        }
    }
    
    @Test
    void setAssignsTheQueenInSpecifiedColumnToRow() {
        var column = 0;
        var newRow = 11;
        var nQueensBoard = new NQueensBoard(board);
        
        nQueensBoard.set(column, newRow);
        
        assertEquals(newRow, nQueensBoard.get(column));
    }
    
    @Test
    void lengthReturnsLengthOfGivenNQueensBoard() {
        assertEquals(board.length, nQueensBoard.length());
    }
    
    @Test
    void equalsReturnsTrueForBoardsThatAreEqualAndToStringMethodWorks() {
        var nQueensBoard2 = new NQueensBoard(nQueensBoard);
        
        assertEquals(nQueensBoard, nQueensBoard2);
        assertEquals(nQueensBoard.toString(), nQueensBoard2.toString());
    }
    
    @Test
    void equalsReturnsFalseForBoardsThatAreNotEqualAndToStringMethodWorks() {
        var nQueensBoard2 = new NQueensBoard(nQueensBoard);
        nQueensBoard2.set(0, 1);
        
        assertNotEquals(nQueensBoard, nQueensBoard2);
        assertNotEquals(nQueensBoard.toString(), nQueensBoard2.toString());
    }
}
