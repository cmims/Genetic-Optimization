package com.slethron.geneticoptimization.type.test;

import com.slethron.geneticoptimization.type.NQueensBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NQueensBoardTest {
    @Test
    void getReturnsRowForQueenInColumn() {
        var column = 0;
        var board = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        var nQueensBoard = new NQueensBoard(board);
        
        assertEquals(board[0], nQueensBoard.get(column));
    }
    
    @Test
    void setAssignsTheQueenInSpecifiedColumnToRow() {
        var column = 0;
        var newRow = 11;
        var nQueensBoard = new NQueensBoard(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        
        nQueensBoard.set(column, newRow);
        
        assertEquals(newRow, nQueensBoard.get(column));
    }
    
    @Test
    void lengthReturnsLengthOfGivenNQueensBoard() {
        var length = 12;
        var nQueensBoard = new NQueensBoard(new int[length]);
        
        assertEquals(length, nQueensBoard.length());
        
    }
    
    @Test
    void numberOfConflictsReturnsNumberOfConflictsForGivenNQueensBoard() {
        var nQueensBoard = new NQueensBoard(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        var expectedNumberOfConflicts = 77;
        var numberOfConflicts = nQueensBoard.numberOfConflicts();
        assertEquals(expectedNumberOfConflicts, numberOfConflicts);
    }
    
    @Test
    void equalsReturnsFalseForBoardsThatAreNotEqualAndToStringMethodWorks() {
        var nQueensBoardA = new NQueensBoard(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        var nQueensBoardB = new NQueensBoard(nQueensBoardA);
        nQueensBoardB.set(0, 1);
        
        assertNotEquals(nQueensBoardA, nQueensBoardB);
        assertNotEquals(nQueensBoardA.toString(), nQueensBoardB.toString());
    }
    
    @Test
    void equalsReturnsTrueForBoardsThatAreEqualAndToStringMethodWorks() {
        var nQueensBoardA = new NQueensBoard(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        var nQueensBoardB = new NQueensBoard(nQueensBoardA);
        
        assertEquals(nQueensBoardA, nQueensBoardB);
        assertEquals(nQueensBoardA.toString(), nQueensBoardB.toString());
    }
}
