package com.slethron.evolution.population.test;

import com.slethron.evolution.individual.StringEvolvable;
import com.slethron.evolution.population.NQueensPopulation;
import com.slethron.evolution.population.StringPopulation;
import com.slethron.evolution.util.RandomUtil;
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
    
    @Test
    void stringMatchProblem_evolveToHelloWorld() {
        var population = new StringPopulation(10000, "Hello, World!");
        System.out.println(population.evolve());
    }
    
    @Test
    void generateIndividualFromParents() {
        String target = "Hello, World!";
        var parentA = new StringEvolvable(target);
        var parentB = new StringEvolvable(target);
        
        var population = new StringPopulation(10000, target);
        System.out.println(parentA.source());
        System.out.println(parentB.source());
        System.out.println(population.generateIndividualFromParents(parentA, parentB).source());
    }
    
    @Test
    void generateLargeIndividualFromParents() {
        String target = RandomUtil.generateRandomString(10000);
        var parentA = new StringEvolvable(target);
        var parentB = new StringEvolvable(target);
        
        var population = new StringPopulation(10000, target);
        System.out.println(parentA.source());
        System.out.println(parentB.source());
        System.out.println(population.generateIndividualFromParents(parentA, parentB).source());
    }
}
