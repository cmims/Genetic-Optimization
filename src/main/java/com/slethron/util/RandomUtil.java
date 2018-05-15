package com.slethron.util;

import com.slethron.geneticoptimization.type.BitString;
import com.slethron.geneticoptimization.type.NQueensBoard;

import java.util.Random;

public class RandomUtil {
    private static final Random RANDOM = new Random();
    
    private RandomUtil() {}
    
    public static String generateRandomString(int length) {
        var sb = new StringBuilder();
        for (var i = 0; i < length; i++) {
            sb.append(Character.toChars(RANDOM.nextInt(127 - 32) + 32));
        }
        
        return sb.toString();
    }
    
    public static BitString generateRandomBitString(int length) {
        var bits = new boolean[length];
        
        for (var i = 0; i < length; i++) {
            bits[i] = RANDOM.nextBoolean();
        }
        
        return new BitString(bits);
    }
    
    public static NQueensBoard generateRandomNQueensBoard(int n) {
        var board = new int[n];
        for (var i = 0; i < n; i++) {
            board[i] = RANDOM.nextInt(n);
        }
        
        return new NQueensBoard(board);
    }
}
