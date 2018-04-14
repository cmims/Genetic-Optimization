package com.slethron.evolution.population.test;

import com.slethron.evolution.population.NQueensPopulation;
import com.slethron.evolution.population.interfaces.Population;
import org.junit.jupiter.api.Test;

class PopulationTest {
    
    @Test
    void nQueensProblem_getSolutionFor8Queens() {
        var population = new NQueensPopulation(10000, 8);
        System.out.println(population.evolve().source());
    }
    
    @Test
    void nQueensProblem_getSolutionFor12Queens() {
        var population = new NQueensPopulation(10000, 12);
        System.out.println(population.evolve().source());
    }
    
    @Test
    void nQueensProblem_getSolutionFor24Queens() {
        var population = new NQueensPopulation(10000, 24);
        System.out.println(population.evolve().source());
    }
    
    @Test
    void nQueensProblem_getSolutionFor48Queens() {
        var population = new NQueensPopulation(10000, 48);
        System.out.println(population.evolve().source());
    }
}
