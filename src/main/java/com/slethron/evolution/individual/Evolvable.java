package com.slethron.evolution.individual;

public abstract class Evolvable<T> {
    private T source;
    
    public Evolvable(T source) {
        this.source = source;
    }
    
    public Evolvable next() {
        source = mutate();
        
        return this;
    }
    
    public T source() {
        return source;
    }
    
    public abstract double fitness();
    
    public abstract T mutate();
}
