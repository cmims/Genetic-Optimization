package com.slethron.util.test;

import com.slethron.util.RandomUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomUtilTest {
    @Test
    void generateRandomStringOf25Characters() {
        var length = 25;
        var randomString = RandomUtil.generateRandomString(length);
        assertEquals(length, randomString.length());
    }
}
