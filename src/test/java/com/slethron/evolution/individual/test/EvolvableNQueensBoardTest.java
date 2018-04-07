package com.slethron.evolution.individual.test;

import com.slethron.evolution.individual.EvolvableNQueensBoard;
import com.slethron.evolution.type.NQueensBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EvolvableNQueensBoardTest {
    private EvolvableNQueensBoard board;
    
    @BeforeEach
    void before() {
        board = new EvolvableNQueensBoard(new NQueensBoard(6));
    }
    
    @Test
    void findSolution() {
        board.evolve();

        System.out.println(board.getSource().toString());
    }
}
