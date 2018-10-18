package com.slethron.geneticoptimization.problem.test;

import com.slethron.geneticoptimization.problem.BitStringProblem;
import com.slethron.geneticoptimization.util.RandomGeneratorUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BitStringProblemTest {
    private int length;
    private BitStringProblem bitStringProblem;
    
    @Before
    public void beforeEach() {
        length = 10;
        bitStringProblem = new BitStringProblem(length);
    }
    
    @Test
    public void generatePopulationOfSize100OfLength10BitStrings() {
        var size = 100;
        var population = bitStringProblem.generateInitialPopulation(size);
        
        assertEquals(size, population.size());
        for (var individual : population) {
            assertNotNull(individual);
            assertEquals(length, individual.length());
        }
    }
    
    @Test
    public void generateIndividualFromParentsGeneratesChildWithElementsFromOneOrBothParents() {
        var parentA = RandomGeneratorUtil.generateRandomBitString(length);
        var parentB = RandomGeneratorUtil.generateRandomBitString(length);
        
        var child = bitStringProblem.generateIndividualFromParents(parentA, parentB);
        
        assertEquals(length, child.length());
        for (var i = 0; i < child.length(); i++) {
            assertTrue(child.get(i) == parentA.get(i) || child.get(i) == parentB.get(i));
        }
    }
}
