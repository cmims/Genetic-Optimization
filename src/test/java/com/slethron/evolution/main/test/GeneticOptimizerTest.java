package com.slethron.evolution.main.test;

import com.slethron.evolution.individual.interfaces.Evolvable;
import com.slethron.evolution.main.GeneticOptimizer;
import com.slethron.evolution.population.NQueensBoardPopulation;
import com.slethron.evolution.population.interfaces.Population;
import com.slethron.evolution.type.NQueensBoard;
import org.junit.jupiter.api.Test;

class GeneticOptimizerTest {
    private GeneticOptimizer geneticOptimizer;
    
    @Test
    void findSolutionFor8Queens() {
        Evolvable result = GeneticOptimizer.solve(new NQueensBoardPopulation(1000, 64));
        System.out.println(result.getSource());
    }
    
    @Test
    void findSolutionFor12Queens() {
        GeneticOptimizer.solve(new NQueensBoardPopulation(1000));
    }
}
