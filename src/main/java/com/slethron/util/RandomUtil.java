package com.slethron.util;

import java.util.Random;

public class RandomUtil {
    private RandomUtil() {}
    
    public static String generateRandomString(int length) {
        var rand = new Random();
        var sb = new StringBuilder();
        for (var i = 0; i < length; i++) {
            sb.append(Character.toChars(rand.nextInt(127 - 32) + 32));
        }
        
        return sb.toString();
    }
}
