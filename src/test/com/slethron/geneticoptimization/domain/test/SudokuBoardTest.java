package com.slethron.geneticoptimization.domain.test;

import com.slethron.geneticoptimization.domain.SudokuBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SudokuBoardTest {
    private SudokuBoard board;

    @BeforeEach
    void init() {
        board = new SudokuBoard();
        board.set(0, 0, 1);
    }

    @Test
    void equalsTest() {
        var board2 = new SudokuBoard(board);
        assertEquals(board, board2);
        board2.set(0, 0, 2);
        assertNotEquals(board.get(0, 0), board2.get(0, 0));
        assertNotEquals(board, board2);
    }
}
