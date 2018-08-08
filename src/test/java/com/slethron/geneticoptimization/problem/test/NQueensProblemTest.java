package com.slethron.geneticoptimization.problem.test;

import com.slethron.geneticoptimization.domain.NQueensBoard;
import com.slethron.geneticoptimization.problem.NQueensProblem;
import com.slethron.geneticoptimization.util.RandomGeneratorUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NQueensProblemTest {
    private int n;
    private NQueensProblem nQueensProblem;
    
    @Before
    public void beforeEach() {
        n = 12;
        nQueensProblem = new NQueensProblem(n);
    }
    
    @Test
    public void generatePopulationOfSize100OfNEquals12Boards() {
        var size = 100;
        var population = nQueensProblem.generatePopulation(size);
        
        assertEquals(size, population.size());
        for (var nQueensBoard : population) {
            assertNotNull(nQueensBoard);
            assertEquals(n, nQueensBoard.length());
        }
    }
    
    @Test
    public void generateIndividualFromParentsGeneratesChildThatHasElementsFromOneOrBothParents() {
        var parentA = RandomGeneratorUtil.generateRandomNQueensBoard(n);
        var parentB = RandomGeneratorUtil.generateRandomNQueensBoard(n);
        
        var child = nQueensProblem.generateIndividualFromParents(parentA, parentB);
        
        assertEquals(n, child.length());
        for (var i = 0; i < child.length(); i++) {
            assertTrue(child.get(i) == parentA.get(i) || child.get(i) == parentB.get(i));
        }
    }
    
    @Test
    public void getFitnessOfSpecifiedBoard() {
        var expectedFitness = 78;
        var board = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        var nQueensBoard = new NQueensBoard(board);
    
        assertEquals(expectedFitness, nQueensProblem.fitness(nQueensBoard), 0);
    }
}
