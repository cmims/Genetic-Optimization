package com.slethron.evolution.individual.impl;

import com.slethron.evolution.individual.Evolvable;
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
        var column = ThreadLocalRandom.current().nextInt(source().length());
        var row = ThreadLocalRandom.current().nextInt(source().length());
        
        var mutated = new NQueensBoard(source());
        mutated.set(column, row);
        
        return mutated;
    }
    
    @Override
    public String toString() {
        return source().toString();
    }
    
    public static class NQueensEvolvableComparator implements Comparator<NQueensEvolvable> {
        @Override
        public int compare(NQueensEvolvable o1, NQueensEvolvable o2) {
            return Double.compare(o1.fitness(), o2.fitness());
        }
    }
}

