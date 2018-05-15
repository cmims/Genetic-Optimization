package com.slethron.geneticoptimization.problem.test;

import com.slethron.geneticoptimization.problem.BitStringProblem;
import com.slethron.geneticoptimization.type.BitString;
import com.slethron.util.RandomUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BitStringProblemTest {
    private int length;
    private BitStringProblem bitStringProblem;
    
    @BeforeEach
    void beforeEach() {
        length = 10;
        bitStringProblem = new BitStringProblem(length);
    }
    
    @Test
    void generateInitialPopulationOfSize100OfLength10BitStrings() {
        var size = 100;
        var population = bitStringProblem.generateInitialPopulation(size);
        
        assertEquals(size, population.size());
        for (BitString individual : population) {
            assertNotNull(individual);
            assertEquals(length, individual.length());
        }
    }
    
    @Test
    void generateIndividualFromParentsGeneratesChildWithElementsFromOneOrBothParents() {
        var parentA = RandomUtil.generateRandomBitString(length);
        var parentB = RandomUtil.generateRandomBitString(length);
        
        var child = bitStringProblem.generateIndividualFromParents(parentA, parentB);
        
        assertEquals(length, child.length());
        for (var i = 0; i < child.length(); i++) {
            assertTrue(child.get(i) == parentA.get(i) || child.get(i) == parentB.get(i));
        }
    }
}
