package com.slethron.evolution.test;

import com.slethron.evolution.impl.EvolvableNQueensBoard;
import com.slethron.evolution.entities.NQueensBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EvolvableNQueensBoardTest {
    private EvolvableNQueensBoard board;
    
    @BeforeEach
    void before() {
        board = new EvolvableNQueensBoard(new NQueensBoard(14));
    }
    
    @Test
    void findSolution() {
        board.evolve();
        
        System.out.println(board.getSource().toString());
    }
}
