package com.slethron.evolution.individual;

import com.slethron.evolution.individual.interfaces.Evolvable;
import com.slethron.evolution.type.NQueensBoard;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class NQueensEvolvable extends Evolvable<NQueensBoard> {
    public NQueensEvolvable(NQueensBoard board) {
        super(board);
    }
    
    @Override
    public double fitness() {
        return source().numberOfConflicts();
    }
    
    @Override
    public NQueensBoard mutate() {
        if (ThreadLocalRandom.current().nextInt(99) < 24) {
            var column = ThreadLocalRandom.current().nextInt(source().length());
            var row = ThreadLocalRandom.current().nextInt(source().length());
    
            if (row > source().length() - 1) {
                row = 0;
            } else if (row < 0) {
                row = source().length() - 1;
            }
    
            var mutated = new NQueensBoard(source());
            mutated.set(column, row);
    
            return mutated;
        } else {
            return source();
        }
    }
    
    public static class NQueensEvolvableComparator implements Comparator<NQueensEvolvable> {
        @Override
        public int compare(NQueensEvolvable o1, NQueensEvolvable o2) {
            return Double.compare(o2.fitness(), o1.fitness());
        }
    }
}

