package com.slethron.geneticoptimization.domain.test;

import com.slethron.geneticoptimization.domain.BitString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitStringTest {
    private boolean[] bits;
    private BitString bitString;
    
    @BeforeEach
    void init() {
        bits = new boolean[] { true, true, true, false, false };
        bitString = new BitString(bits);
    }
    
    @Test
    void equalsTest() {
        var other = new BitString(bits.clone());
        assertEquals(bitString, other);
        
        other.set(0, !other.get(0));
        assertNotEquals(bitString, other);
    }
    
    @Test
    void cloneTest() {
        var other = new BitString(bitString);
        assertEquals(bitString, other);
        
        other.set(0, !other.get(0));
        assertNotEquals(bitString, other);
    }
}
