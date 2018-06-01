package com.slethron.geneticoptimization.problem.test;

import com.slethron.geneticoptimization.problem.NQueensProblem;
import com.slethron.util.RandomUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NQueensProblemTest {
    private int n;
    private NQueensProblem nQueensProblem;
    
    @BeforeEach
    void beforeEach() {
        n = 12;
        nQueensProblem = new NQueensProblem(n);
    }
    
    @Test
    void generateInitialPopulationOfSize100OfNEquals12Boards() {
        var size = 100;
        var population = nQueensProblem.generateInitialPopulation(size);
        
        assertEquals(size, population.size());
        for (var nQueensBoard : population) {
            assertNotNull(nQueensBoard);
            assertEquals(n, nQueensBoard.length());
        }
    }
    
    @Test
    void generateIndividualFromParentsGeneratesChildThatHasElementsFromOneOrBothParents() {
        var parentA = RandomUtil.generateRandomNQueensBoard(n);
        var parentB = RandomUtil.generateRandomNQueensBoard(n);
        
        var child = nQueensProblem.generateIndividualFromParents(parentA, parentB);
        
        assertEquals(n, child.length());
        for (var i = 0; i < child.length(); i++) {
            assertTrue(child.get(i) == parentA.get(i) || child.get(i) == parentB.get(i));
        }
    }
}
