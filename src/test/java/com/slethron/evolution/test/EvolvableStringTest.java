package com.slethron.evolution.test;

import com.slethron.evolution.EvolvableString;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EvolvableStringTest {
    EvolvableString es;

    String source = "!dlroW ,olleH";
    String target = "Hello, World!";

    @Before
    public void before() {
        es = new EvolvableString(source);
    }

    @Test
    public void evolveEvolvesStringIntoTargetString() {
        es.evolve(target);
        assertEquals(target, es.getSource());
    }
}
