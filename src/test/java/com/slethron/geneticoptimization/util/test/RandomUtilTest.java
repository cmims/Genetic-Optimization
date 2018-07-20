package com.slethron.geneticoptimization.util.test;

import com.slethron.geneticoptimization.util.RandomUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RandomUtilTest {
    @Test
    public void generateRandomStringOf25Characters() {
        var length = 25;
        var randomString = RandomUtil.generateRandomString(length);
        assertEquals(length, randomString.length());
    }
    
    @Test
    public void generateRandomBitStringOf25Bits() {
        var length = 25;
        var randomBitString = RandomUtil.generateRandomBitString(length);
        for (var i = 0; i < length; i++) {
            assertTrue(randomBitString.get(i) || !randomBitString.get(i));
        }
    }
    
    @Test
    public void generateRandomNQueensBoardOfNEquals12() {
        var n = 12;
        var randomNQueensBoard = RandomUtil.generateRandomNQueensBoard(n);
        for (var i = 0; i < n; i++) {
            assertTrue(randomNQueensBoard.get(i) >= 0 && randomNQueensBoard.get(i) < n);
        }
    }
    
    @Test
    public void generateRandomKnapsackOf10Items() {
    
    }
}
