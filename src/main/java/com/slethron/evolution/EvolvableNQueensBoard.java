package com.slethron.evolution;

import com.slethron.evolution.entities.NQueensBoard;
import com.slethron.evolution.interfaces.Evolvable;

import java.util.Random;

public class EvolvableNQueensBoard extends Evolvable<NQueensBoard> {
    private Random random;
    
    public EvolvableNQueensBoard(NQueensBoard board) {
        super(board);
        random = new Random();
    }
    
    @Override
    public double fitness(NQueensBoard source, NQueensBoard target) {
        return source.orderConflict();
    }
    
    @Override
    public NQueensBoard mutate(NQueensBoard source) {
        var c = random.nextInt(source.getLength());
        var flip = random.nextBoolean() ? 1 : -1;
        var mutation = source.get(c) + flip;
        
        if (mutation > source.getLength() - 1) {
            mutation = 0;
        } else if (mutation < 0) {
            mutation = source.getLength() -1;
        }
        
        source.set(c, mutation);
        
        return source;
    }
}
