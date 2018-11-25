package com.slethron.geneticoptimization.problem.test;

import com.slethron.geneticoptimization.domain.SudokuBoard;
import com.slethron.geneticoptimization.problem.SudokuProblem;
import com.slethron.geneticoptimization.util.RandomGeneratorUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuProblemTest {
    private SudokuBoard sudokuBoard;
    private SudokuProblem sudokuProblem;
    
    @Before
    public void before() {
        var numberOfFilledCells = 40;
        sudokuBoard = RandomGeneratorUtil.generateRandomSudokuBoard(numberOfFilledCells);
        sudokuProblem = new SudokuProblem(sudokuBoard);
    }
    
    @Test
    public void generatePopulationOfSize100BoardsWithSameStaticCells() {
        var populationSize = 100;
        var population = sudokuProblem.generateInitialPopulation(populationSize);
        
        assertEquals(populationSize, population.size());
        for (var board : population) {
            assertNotNull(board);
            for (var row = 0; row < SudokuBoard.SIZE; row++) {
                for (var column = 0; column < SudokuBoard.SIZE; column++) {
                    assertArrayEquals(sudokuBoard.getStaticCells(), board.getStaticCells());
                }
            }
        }
    }
    
    public void generateIndividualFromParentsGeneratesChildThatHasSameStaticCellsAndElementsOfOneOrBothParents() {
        var parentA = sudokuBoard;
        var parentB = sudokuProblem.generateInitialPopulation(1).get(0);
        
        var child = sudokuProblem.generateIndividualFromParents(parentA, parentB);
        
        assertArrayEquals(parentB.getStaticCells(), child.getStaticCells());
        assertArrayEquals(parentA.getStaticCells(), child.getStaticCells());
        
        for (var row = 0; row < SudokuBoard.SIZE; row++) {
            for (var column = 0; column < SudokuBoard.SIZE; column++) {
                assertTrue(child.get(row, column) == parentA.get(row, column)
                        || child.get(row, column) == parentB.get(row, column));
            }
        }
    }
}
