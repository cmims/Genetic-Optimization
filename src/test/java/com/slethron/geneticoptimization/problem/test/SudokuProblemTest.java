package com.slethron.geneticoptimization.problem.test;

import com.slethron.geneticoptimization.domain.SudokuBoard;
import com.slethron.geneticoptimization.problem.SudokuProblem;
import com.slethron.geneticoptimization.util.SudokuBoardUtil;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuProblemTest {
    private SudokuProblem sudokuProblem;
    private SudokuBoard boardX;
    
    @Before
    public void before() {
        sudokuProblem = new SudokuProblem();
        boardX = SudokuBoardUtil.getUnsolvedBoardX();
        
        for (var column = 0; column < 9; column++) {
            for (var row = 0; row < 9; row++) {
                if (boardX.get(column, row) == 0) {
                    boardX.set(column, row, 1);
                }
            }
        }
    }
    
    @Test
    public void boardXHas1WhereStaticCellsAreNot() {
        for (var i = 0; i < boardX.getBoard().length; i++) {
            for (var j = 0; j < boardX.getBoard().length; j++) {
                if (!boardX.getStaticCells().contains(new Pair<>(i, j))) {
                    assertEquals(1, boardX.get(i, j), 0);
                }
            }
        }
    }
    
    @Test
    public void generateInitialPopulationOfVariationsOfBoardX() {
        var size = 10;
        var population = sudokuProblem.generatePopulationFromIndividual(size, boardX);
        
        assertEquals(size, population.size());
        for (var sudokuBoard : population) {
            assertNotNull(sudokuBoard);
            assertEquals(boardX.getStaticCells(), sudokuBoard.getStaticCells());
        }
    }
    
    @Test
    public void generateIndividualFromParentsGeneratesChildThatHasElementsFromOneOrBothParents() {
        var parents = sudokuProblem.generatePopulationFromIndividual(2, boardX);
        var parentA = parents.get(0);
        var parentB = parents.get(1);
        
        var child = sudokuProblem.generateIndividualFromParents(parentA, parentB);
        
        assertEquals(boardX.getStaticCells(), child.getStaticCells());
        for (var i = 0; i < child.getBoard().length; i++) {
            for (var j = 0; j < child.getBoard().length; j++) {
                assertTrue(child.get(i, j) == parentA.get(i, j) || child.get(i, j) == parentB.get(i, j));
            }
        }
    }
    
    @Test
    public void getFitnessOfBoardX() {
        var expectedFitness = 542;
        assertEquals(expectedFitness, sudokuProblem.fitness(boardX), 0);
    }
}
