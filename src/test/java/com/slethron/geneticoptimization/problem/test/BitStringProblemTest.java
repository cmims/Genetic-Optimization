package com.slethron.geneticoptimization.problem.test;

import com.slethron.geneticoptimization.problem.BitStringProblem;
import com.slethron.geneticoptimization.util.RandomUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
        var population = bitStringProblem.generatePopulation(size);
        
        assertEquals(size, population.size());
        for (var individual : population) {
            assertNotNull(individual);
            assertEquals(length, individual.length());
        }
    }
    
    @Test
    public void generateIndividualFromParentsGeneratesChildWithElementsFromOneOrBothParents() {
        var parentA = RandomUtil.generateRandomBitString(length);
        var parentB = RandomUtil.generateRandomBitString(length);
        
        var child = bitStringProblem.generateIndividualFromParents(parentA, parentB);
        
        assertEquals(length, child.length());
        for (var i = 0; i < child.length(); i++) {
            assertTrue(child.get(i) == parentA.get(i) || child.get(i) == parentB.get(i));
        }
    }
}
