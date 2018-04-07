package com.slethron.evolution.individual;

import com.slethron.evolution.type.NQueensBoard;
import com.slethron.evolution.individual.interfaces.Evolvable;

import java.util.Comparator;
import java.util.Random;

import static java.util.Objects.isNull;


public class EvolvableNQueensBoard extends Evolvable<NQueensBoard> {
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
        var column = random.nextInt(source.length());
        var dir = random.nextBoolean() ? 1 : -1;
        var mutation = source.get(column) + dir;
        
        if (mutation > source.length() - 1) {
            mutation = 0;
        } else if (mutation < 0) {
            mutation = source.length() - 1;
        }
        
        return new NQueensBoard(source).set(column, mutation);
    }
    
    public static class EvolvableNQueensBoardComparator implements Comparator<EvolvableNQueensBoard> {
        @Override
        public int compare(EvolvableNQueensBoard o1, EvolvableNQueensBoard o2) {
            if (o1.getSource().orderConflict() < o2.getSource().orderConflict()) {
                return -1;
            } else if (o1.getSource().orderConflict() == o2.getSource().orderConflict()) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
