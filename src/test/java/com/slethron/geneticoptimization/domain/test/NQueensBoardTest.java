package com.slethron.geneticoptimization.domain.test;

import com.slethron.geneticoptimization.domain.NQueensBoard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class NQueensBoardTest {
    private NQueensBoard nQueensBoard;

    @Before
    public void before() {
        int[] board = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        nQueensBoard = new NQueensBoard(board);
    }

    @Test
    public void equalsReturnsTrueForBoardsThatAreTheSameAndFalseForBoardsThatArent() {
        var nQueensBoard2 = new NQueensBoard(nQueensBoard);
        assertEquals(nQueensBoard, nQueensBoard2);
        
        nQueensBoard2.set(0, nQueensBoard2.get(0) + 1);
        assertNotEquals(nQueensBoard, nQueensBoard2);
    }
}
