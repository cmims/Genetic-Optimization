package com.slethron.geneticoptimization.type.test;

import com.slethron.geneticoptimization.type.BitString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BitStringTest {
    private boolean[] bits;
    private BitString bitString;
    
    @BeforeEach
    void beforeEach() {
        bits = new boolean[] { true, true, true, false, false };
        bitString = new BitString(bits);
    }
    
    @Test
    void getReturnsBitAtIndex() {
        for (var i = 0; i < bitString.length(); i++) {
            assertEquals(bits[i], bitString.get(i));
        }
    }
}
