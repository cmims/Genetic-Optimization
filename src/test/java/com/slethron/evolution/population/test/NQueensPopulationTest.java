package com.slethron.evolution.population.test;

import com.slethron.evolution.population.NQueensPopulation;
import com.slethron.util.NanoTimer;
import org.junit.jupiter.api.Test;

class NQueensPopulationTest {
    private static final NanoTimer NANO_TIMER = new NanoTimer();
    
    @Test
    void getSolutionFor8Queens() {
        var population = new NQueensPopulation(1000, 8);
        
        NANO_TIMER.start();
        var result = population.evolve();
        NANO_TIMER.stop();
        
        System.out.println(result);
        System.out.println("Solution found in " + NANO_TIMER.toString());
    }
    
    @Test
    void getSolutionFor12Queens() {
        var population = new NQueensPopulation(1000, 12);
        
        NANO_TIMER.start();
        var result = population.evolve();
        NANO_TIMER.stop();
        
        System.out.println(result);
        System.out.println("Solution found in " + NANO_TIMER.toString());
    }
    
    @Test
    void getSolutionFor24Queens() {
        var population = new NQueensPopulation(1000, 24);
        
        NANO_TIMER.start();
        var result = population.evolve();
        NANO_TIMER.stop();
        
        System.out.println(result);
        System.out.println("Solution found in " + NANO_TIMER.toString());
    }
    
    @Test
    void getSolutionFor48Queens() {
        var population = new NQueensPopulation(1000, 48);
        NANO_TIMER.start();
        var result = population.evolve();
        NANO_TIMER.stop();
        
        System.out.println(result);
        System.out.println("Solution found in " + NANO_TIMER.toString());
    }
}
