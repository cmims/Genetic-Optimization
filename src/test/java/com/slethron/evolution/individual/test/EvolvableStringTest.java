package com.slethron.evolution.individual.test;

import com.slethron.evolution.individual.EvolvableString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EvolvableStringTest {
    private EvolvableString es;
    private String target = "Hello, World!";
    
    @BeforeEach
    void before() {
        String source = "!dlroW ,olleH";
        es = new EvolvableString(source, target);
    }

    @Test
    void evolveEvolvesStringIntoTargetString() {
        es.evolve();
        assertEquals(target, es.getSource());
    }
}
