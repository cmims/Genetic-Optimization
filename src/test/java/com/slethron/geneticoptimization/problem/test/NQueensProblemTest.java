package com.slethron.geneticoptimization.problem.test;

import com.slethron.geneticoptimization.problem.NQueensProblem;
import com.slethron.geneticoptimization.type.NQueensBoard;
import com.slethron.util.NanoTimer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NQueensProblemTest {
    private static final NanoTimer NANO_TIMER = new NanoTimer();
    
    @Test
    void solveFor12Queens() {
        var n = 12;
        var nQueensProblem = new NQueensProblem(n);
        
        NANO_TIMER.start();
        var solution = nQueensProblem.solve(10000, 1000, .05);
        NANO_TIMER.stop();
        
        System.out.println("Solution: " + solution);
        System.out.println("Solution for n=" + n + " found in " + NANO_TIMER.toString());
    }
    
    @Test
    void solveFor48Queens() {
        var n = 48;
        var nQueensProblem = new NQueensProblem(n);
        
        NANO_TIMER.start();
        var solution = nQueensProblem.solve(10000, 1000, .05);
        NANO_TIMER.stop();
        
        System.out.println("Solution: " + solution);
        System.out.println("Solution for n=" + " found in " + NANO_TIMER.toString());
    }
    
    @Test
    void generateInitialPopulationOfSize100OfNEquals12Boards() {
        var size = 100;
        var n = 12;
        var nQueensProblem = new NQueensProblem(n);
        var population = nQueensProblem.generateInitialPopulation(size);
        
        assertEquals(size, population.size());
        for (NQueensBoard nQueensBoard : population) {
            assertNotNull(nQueensBoard);
            assertEquals(n, nQueensBoard.length());
        }
    }
    
    @Test
    void generateIndividualFromParentsGeneratesChildThatHasElementsFromOneOrBothParents() {
        var length = 12;
        var parentA = NQueensBoard.generateRandomBoard(length);
        var parentB = NQueensBoard.generateRandomBoard(length);
        
        var nQueensProblem = new NQueensProblem(length);
        var child = nQueensProblem.generateIndividualFromParents(parentA, parentB);
        
        assertEquals(length, child.length());
        for (var i = 0; i < child.length(); i++) {
            assertTrue(child.get(i) == parentA.get(i) || child.get(i) == parentB.get(i));
        }
    }
    
    @Test
    void mutateChangesTheQueenInOneRandomColumnToARandomRow() {
        var nQueensBoard = new NQueensBoard(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        var nQueensProblem = new NQueensProblem(nQueensBoard.length());
        var mutated = nQueensProblem.mutate(nQueensBoard);
        assertNotEquals(nQueensBoard, mutated);
    }
}
