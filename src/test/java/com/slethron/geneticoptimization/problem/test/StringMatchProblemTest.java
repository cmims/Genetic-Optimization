package com.slethron.geneticoptimization.problem.test;

import com.slethron.geneticoptimization.problem.StringMatchProblem;
import com.slethron.util.NanoTimer;
import com.slethron.util.RandomUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringMatchProblemTest {
    private static final NanoTimer NANO_TIMER = new NanoTimer();
    
    @Test
    void evolveToHelloWorld() {
        var target = "Hello, World!";
        var stringMatchProblem = new StringMatchProblem(target);
        var solution = stringMatchProblem.solve(10000, 100, .05);
        System.out.println(solution);
        assertEquals(target, solution);
    }
    
    @Test
    void evolveToSomeLargeString() {
        var target = RandomUtil.generateRandomString(100);
        var stringMatchProblem = new StringMatchProblem(target);
        var solution = stringMatchProblem.solve(10000, 1000, .05);
        System.out.println(solution);
        assertEquals(target, solution);
    }
    
    @Test
    void generateInitialPopulationOfSize100OfLength10Strings() {
        var size = 100;
        var target = "Hello, World!";
        var stringMatchProblem = new StringMatchProblem(target);
        var population = stringMatchProblem.generateInitialPopulation(size);
        
        assertEquals(size, population.size());
        for (String item : population) {
            assertNotNull(item);
            assertEquals(target.length(), item.length());
        }
    }
    
    @Test
    void generateIndividualFromParentsGeneratesChildWithElementsFromOneOrBothParents() {
        var target = "Hello, World!";
        var parentA = RandomUtil.generateRandomString(target.length());
        var parentB = RandomUtil.generateRandomString(target.length());
        
        var stringMatchProblem = new StringMatchProblem(target);
        var child = stringMatchProblem.generateIndividualFromParents(parentA, parentB);
        
        assertEquals(target.length(), child.length());
        for (var i = 0; i < child.length(); i++) {
            assertTrue(child.charAt(i) == parentA.charAt(i) || child.charAt(i) == parentB.charAt(i));
        }
    }
}