package com.slethron.evolution.test;

import com.slethron.evolution.impl.EvolvableString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EvolvableStringTest {
    private EvolvableString es;
    
    @BeforeEach
    void before() {
        String source = "!dlroW ,olleH";
        es = new EvolvableString(source);
    }

    @Test
    void evolveEvolvesStringIntoTargetString() {
        String target = "Hello, World!";
        es.evolve(target);
        assertEquals(target, es.getSource());
    }
}
