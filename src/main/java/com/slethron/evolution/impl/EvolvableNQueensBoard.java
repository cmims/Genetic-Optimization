package com.slethron.evolution.impl;

import com.slethron.evolution.entities.NQueensBoard;
import com.slethron.evolution.interfaces.NaturalEvolvable;
import com.slethron.evolution.interfaces.TargetedEvolvable;

import java.util.Random;

public class EvolvableNQueensBoard extends NaturalEvolvable<NQueensBoard> {
    private Random random;
    
    public EvolvableNQueensBoard(NQueensBoard board) {
        super(board);
        random = new Random();
    }
    
    @Override
    public double fitness(NQueensBoard source) {
        return source.orderConflict();
    }
    
    @Override
    public NQueensBoard mutate(NQueensBoard source) {
        var board = source;
        var c = random.nextInt(board.getLength());
        var flip = random.nextBoolean() ? 1 : -1;
        var mutation = board.get(c) + flip;
        
        if (mutation > board.getLength() - 1) {
            mutation = 0;
        } else if (mutation < 0) {
            mutation = board.getLength() -1;
        }
        
        board.set(c, mutation);
        
        return board;
    }
}
