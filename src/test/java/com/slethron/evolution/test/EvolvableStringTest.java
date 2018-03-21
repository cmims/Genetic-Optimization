package com.slethron.evolution.test;

import com.slethron.evolution.EvolvableString;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EvolvableStringTest {
    EvolvableString es;

    String source = "!dlroW ,olleH";
    String target = "Hello, World!";

    String sourceInvalidChar = "!dlroW ,®lleH";
    String targetInvalidChar = "Hello, World®";

    String targetInvalidLength = "Hello, World! ";


    @Before
    public void before() {
        es = new EvolvableString(source);
    }

    @Test
    public void evolveEvolvesStringIntoTargetString() {
        es.evolve(target);
        assertEquals(target, es.getSource());
    }

    @Test(expected = IllegalArgumentException.class)
    public void evolveThrowsIllegalArgumentExceptionWhenTargetLengthNotEqualsSourceLength() {
        es.evolve(targetInvalidLength);
    }

    @Test(expected = IllegalArgumentException.class)
    public void evolveThrowsIllegalArgumentExceptionWhenTargetHasInvalidChar() {
        es.evolve(targetInvalidChar);
    }

    @Test(expected = IllegalArgumentException.class)
    public void evolveThrowsIllegalArgumentExceptionWhenSourceHasInvalidChar() {
        es.setSource(sourceInvalidChar);
        es.evolve(target);
    }
}
