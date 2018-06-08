package com.slethron.geneticoptimization.domain.test;

import com.slethron.geneticoptimization.domain.BitString;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BitStringTest {
    private boolean[] bits;
    private BitString bitString;
    
    @Before
    public void beforeEach() {
        bits = new boolean[] { true, true, true, false, false };
        bitString = new BitString(bits);
    }
    
    @Test
    public void getReturnsBitAtIndex() {
        for (var i = 0; i < bitString.length(); i++) {
            assertEquals(bits[i], bitString.get(i));
        }
    }
}
