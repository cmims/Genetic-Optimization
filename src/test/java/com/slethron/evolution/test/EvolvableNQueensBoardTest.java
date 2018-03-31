package com.slethron.evolution.test;

import com.slethron.evolution.EvolvableNQueensBoard;
import com.slethron.evolution.entities.NQueensBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EvolvableNQueensBoardTest {
    private EvolvableNQueensBoard board;
    
    @BeforeEach
    void before() {
        board = new EvolvableNQueensBoard(new NQueensBoard(12));
    }
    
    @Test
    void findSolution() {
        board.evolve(new NQueensBoard(0));
    }
}
