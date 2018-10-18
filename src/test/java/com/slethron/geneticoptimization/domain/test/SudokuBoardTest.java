package com.slethron.geneticoptimization.domain.test;

import com.slethron.geneticoptimization.domain.SudokuBoard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SudokuBoardTest {
    private SudokuBoard board;

    @Before
    public void before() {
        board = new SudokuBoard();
        board.set(0, 0, 1);
    }

    @Test
    public void equalsReturnsTrueForBoardsThatAreTheSameAndFalseForBoardsThatArentAndCloneWorksProperly() {
        var board2 = board.clone();
        assertEquals(board.get(0, 0), board2.get(0, 0));
        assertEquals(board, board2);
        board2.set(0, 0, 2);
        assertNotEquals(board.get(0, 0), board2.get(0, 0));
        assertNotEquals(board, board2);
    }
}
