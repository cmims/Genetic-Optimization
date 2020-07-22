package com.slethron.geneticoptimization.domain.test;

import com.slethron.geneticoptimization.domain.NQueensBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NQueensBoardTest {
    private int[] board;
    private NQueensBoard nQueensBoard;

    @BeforeEach
    void init() {
        board = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        nQueensBoard = new NQueensBoard(board);
    }
    
    @Test
    void equalsTest() {
        var other = new NQueensBoard(board.clone());
        assertEquals(nQueensBoard, other);
        
        other.set(0, 1);
        assertNotEquals(nQueensBoard, other);
    }

    @Test
    void cloneTest() {
        var other = new NQueensBoard(nQueensBoard);
        assertEquals(nQueensBoard, other);
        
        other.set(0, 1);
        assertNotEquals(nQueensBoard, other);
    }
}
