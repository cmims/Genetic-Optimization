package com.slethron.evolution.individual.interfaces;

public abstract class Evolvable<T> {
    private T source;
    private double fitVal;
    
    public Evolvable(T source) {
        this.source = source;
        fitVal = fitness(source);
    }
    
    public Evolvable next() {
        var mutated = mutate(source);
        var fitValM = fitness(mutated);
        if (fitValM <= fitVal) {
            fitVal = fitValM;
            source = mutated;
        }
        
        return fitVal == 0 ? this : null;
    }
    
    public void evolve() {
        while (next() != null);
    }
    
    public T getSource() {
        return source;
    }
    
    public abstract double fitness(T source);
    
    public abstract T mutate(T source);
}
