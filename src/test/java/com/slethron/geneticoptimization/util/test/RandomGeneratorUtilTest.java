package com.slethron.geneticoptimization.util.test;

import com.slethron.geneticoptimization.domain.Knapsack;
import com.slethron.geneticoptimization.domain.SudokuBoard;
import com.slethron.geneticoptimization.util.RandomGeneratorUtil;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomGeneratorUtilTest {
    @Test
    void generateRandomStringOf25Characters() {
        var length = 25;
        var randomString = RandomGeneratorUtil.generateRandomString(length);
        assertEquals(length, randomString.length());
    }
    
    @Test
    void generateRandomBitStringOf25Bits() {
        var length = 25;
        var randomBitString = RandomGeneratorUtil.generateRandomBitString(length);
        for (var i = 0; i < length; i++) {
            assertTrue(randomBitString.get(i) || !randomBitString.get(i));
        }
    }
    
    @Test
    void generateRandomNQueensBoardOfNEquals12() {
        var n = 12;
        var randomNQueensBoard = RandomGeneratorUtil.generateRandomNQueensBoard(n);
        for (var i = 0; i < n; i++) {
            assertTrue(randomNQueensBoard.get(i) >= 0 && randomNQueensBoard.get(i) < n);
        }
    }
    
    @Test
    void generateRandomKnapsackOf10Items() {
        // Generate 15 items of weight>=10 and knapsack maxWeight of 150 so test never fails
        var numberOfItems = 15;
        var maxItemWeightValue = 50;
        var random = new Random();
        var maxKnapsackWeight = random.nextInt(100) + 50;
        
        var itemsToPut = IntStream.range(0, numberOfItems)
                .mapToObj(i -> new Knapsack.KnapsackItem(i, random.nextInt(maxItemWeightValue), random.nextInt(maxItemWeightValue)))
                .collect(Collectors.toList());
        
        var randomKnapsack = RandomGeneratorUtil.generateRandomKnapsack(maxKnapsackWeight, itemsToPut);
        
        assertTrue(numberOfItems > randomKnapsack.getItems().size());
        assertEquals(maxKnapsackWeight, randomKnapsack.getMaxWeight());
        for (var item : randomKnapsack.getItems()) {
            assertTrue(itemsToPut.contains(item));
        }
    }
    
    @Test
    void generateRandomSudokuBoardWith40FilledCells() {
        var numberOfEmptyCells = 40;
        var numberOfFilledCells = SudokuBoard.SIZE * SudokuBoard.SIZE - numberOfEmptyCells;
        var randomSudokuBoard = RandomGeneratorUtil.generateRandomSudokuBoard(numberOfFilledCells);
        assertEquals(numberOfEmptyCells, randomSudokuBoard.getEmptyCellCount());
    }
}
