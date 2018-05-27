package com.slethron.util.test;

import com.slethron.util.RandomUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomUtilTest {
    @Test
    void generateRandomStringOf25Characters() {
        var length = 25;
        var randomString = RandomUtil.generateRandomString(length);
        assertEquals(length, randomString.length());
    }
    
    @Test
    void generateRandomBitStringOf25Bits() {
        var length = 25;
        var randomBitString = RandomUtil.generateRandomBitString(length);
        for (var i = 0; i < length; i++) {
            assertTrue(randomBitString.get(i) || !randomBitString.get(i));
        }
    }
    
    @Test
    void generateRandomNQueensBoardOfNEquals12() {
        var n = 12;
        var randomNQueensBoard = RandomUtil.generateRandomNQueensBoard(n);
        for (var i = 0; i < n; i++) {
            assertTrue(randomNQueensBoard.get(i) >= 0 && randomNQueensBoard.get(i) < n);
        }
    }
    
    @Test
    void generateRandomKnapsackOf10Items() {
    
    }
}
