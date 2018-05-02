package com.slethron.util;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomUtil {
    private RandomUtil() {}
    
    public static String generateRandomString(int length) {
        return IntStream.range(0, length)
                .unordered().parallel()
                .mapToObj(i -> ThreadLocalRandom.current().nextInt(32, 127))
                .map(Character::toChars)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
