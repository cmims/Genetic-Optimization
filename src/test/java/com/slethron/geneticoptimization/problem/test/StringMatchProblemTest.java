package com.slethron.geneticoptimization.problem.test;

import com.slethron.geneticoptimization.problem.StringMatchProblem;
import com.slethron.geneticoptimization.util.RandomGeneratorUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringMatchProblemTest {
    private String target;
    private StringMatchProblem stringMatchProblem;
    
    @BeforeEach
    void init() {
        target = "Hello, World!";
        stringMatchProblem = new StringMatchProblem(target);
    }
    
    @Test
    void generatePopulationOfSize100OfLength10Strings() {
        var size = 100;
        var population = stringMatchProblem.generateInitialPopulation(size);
        
        assertEquals(size, population.size());
        for (var item : population) {
            assertNotNull(item);
            assertEquals(target.length(), item.length());
        }
    }
    
    @Test
    void generateIndividualFromParentsGeneratesChildWithElementsFromOneOrBothParents() {
        var parentA = RandomGeneratorUtil.generateRandomString(target.length());
        var parentB = RandomGeneratorUtil.generateRandomString(target.length());
        
        var child = stringMatchProblem.generateIndividualFromParents(parentA, parentB);
        
        assertEquals(target.length(), child.length());
        for (var i = 0; i < child.length(); i++) {
            assertTrue(child.charAt(i) == parentA.charAt(i) || child.charAt(i) == parentB.charAt(i));
        }
    }
}
