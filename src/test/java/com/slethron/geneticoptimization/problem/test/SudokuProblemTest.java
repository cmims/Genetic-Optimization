package com.slethron.geneticoptimization.problem.test;

import com.slethron.geneticoptimization.domain.SudokuBoard;
import com.slethron.geneticoptimization.problem.SudokuProblem;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SudokuProblemTest {
    private SudokuProblem sudokuProblem;
    private SudokuBoard boardX = initBoardX();
    
    @Before
    public void before() {
        sudokuProblem = new SudokuProblem();
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
    
    private SudokuBoard initBoardX() {
        var board = new int[][] {
                { 4, 0, 0, 0, 0, 2, 8, 3, 0 },
                { 0, 8, 0, 1, 0, 4, 0, 0, 2 },
                { 7, 0, 6, 0, 8, 0, 5, 0, 0 },
                { 1, 0, 0, 0, 0, 7, 0, 5, 0 },
                { 2, 7, 0, 5, 0, 0, 0, 1, 9 },
                { 0, 3, 0, 9, 4, 0, 0, 0, 6 },
                { 0, 0, 8, 0, 9, 0, 7, 0, 5 },
                { 3, 0, 0, 8, 0, 6, 0, 9, 0 },
                { 0, 4, 2, 7, 0, 0, 0, 0, 3 }
        };
        
        var staticCells = new ArrayList<Pair<Integer, Integer>>() {{
            for (var i = 0; i < board.length; i++) {
                for (var j = 0; j < board.length; j++) {
                    if (board[i][j] != 0) {
                        add(new Pair<>(i, j));
                    }
                }
            }
        }};
        
        for (var i = 0; i < board.length; i++) {
            for (var j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                }
            }
        }
        
        return new SudokuBoard(board, staticCells);
    }
}
