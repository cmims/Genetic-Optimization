package com.slethron.util;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomUtil {
    private RandomUtil() {}
    
    public static String generateRandomString(int length) {
        return IntStream.range(0, length)
                .unordered().parallel()
                .mapToObj(i -> ThreadLocalRandom.current().nextInt(126 - 32) + 32)
                .map(Character::toChars)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
