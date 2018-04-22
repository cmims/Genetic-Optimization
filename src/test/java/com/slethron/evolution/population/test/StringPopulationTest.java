package com.slethron.evolution.population.test;

import com.slethron.evolution.individual.impl.StringEvolvable;
import com.slethron.evolution.population.impl.StringPopulation;
import com.slethron.util.RandomUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringPopulationTest {
    
    @Test
    void evolveToHelloWorld() {
        var population = new StringPopulation(1000, "Hello, World!");
        System.out.println(population.evolve().source());
    }
    
    @Test
    void evolveToSomeLargeTargetString() {
        String target = RandomUtil.generateRandomString(100);
        System.out.println(target);
        
        var population = new StringPopulation(10000, target);
        var result = population.evolve().source();
    
        System.out.println(result);
        assertEquals(target, result);
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
