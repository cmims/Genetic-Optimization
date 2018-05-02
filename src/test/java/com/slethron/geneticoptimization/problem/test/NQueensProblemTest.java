package com.slethron.geneticoptimization.problem.test;

import com.slethron.geneticoptimization.problem.NQueensProblem;
import com.slethron.geneticoptimization.type.NQueensBoard;
import com.slethron.util.NanoTimer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NQueensProblemTest {
    private static final NanoTimer NANO_TIMER = new NanoTimer();
    
    @Test
    void solveFor12Queens() {
        var n = 12;
        var nQueensProblem = new NQueensProblem(n);
        
        NANO_TIMER.start();
        var solution = nQueensProblem.solve(10000, 1000, .06);
        NANO_TIMER.stop();
        
        System.out.println("Solution: " + solution);
        System.out.println("Solution for n=" + n + " found in " + NANO_TIMER.toString());
    }
    
    @Test
    void generateInitialPopulationOfSize100OfNEquals12Boards() {
        var size = 100;
        var n = 12;
        var nQueensProblem = new NQueensProblem(n);
        var population = nQueensProblem.generateInitialPopulation(100);
        
        assertEquals(100, population.size());
        for (NQueensBoard nQueensBoard : population) {
            assertNotNull(nQueensBoard);
            assertEquals(12, nQueensBoard.length());
        }
    }
    
    @Test
    void getReturnsRowForQueenInColumn() {
        var column = 0;
        var board = new int[] {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        var nQueensBoard = new NQueensBoard(board);
        
        assertEquals(board[0], nQueensBoard.get(column));
    }
    
    @Test
    void setAssignsTheQueenInColumnToRow() {
        var column = 0;
        var newRow = 11;
        var board = new int[] {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        var nQueensBoard = new NQueensBoard(board);
        
        nQueensBoard.set(column, newRow);
        
        assertEquals(newRow, nQueensBoard.get(column));
    }
    
    @Test
    void lengthReturnsLengthOfGivenNQueensBoard() {
        var length = 12;
        var board = new int[12];
        var nQueensBoard = new NQueensBoard(board);
        
        assertEquals(length, nQueensBoard.length());
        
        
    }
}
