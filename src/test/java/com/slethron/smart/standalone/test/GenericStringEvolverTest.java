package com.slethron.smart.standalone.test;

import com.slethron.smart.standalone.GenericStringEvolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenericStringEvolverTest {
    GenericStringEvolver se;
    
    String source = "!dlroW ,olleH";
    String target = "Hello, World!";
    
    String sourceInvalidChar = "!dlroW ,®lleH";
    String targetInvalidChar = "Hello, World®";
    
    String targetInvalidLength = "Hello, World! ";
    
    @BeforeEach
    public void before() {
        se = new GenericStringEvolver(source);
    }
    
    @Test
    public void evolveEvolvesSourceStringIntoTargetString() {
        se.evolve(target);
        assertEquals(target, se.getSource());
    }
    
//    @Test(expected = IllegalArgumentException.class)
//    public void evolveThrowsIllegalArgumentExceptionWhenTargetLengthNotEqualsSourceLength() {
//        se.evolve(targetInvalidLength);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void evolveThrowsIllegalArgumentExceptionWhenTargetHasInvalidChar() {
//        se.evolve(targetInvalidChar);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void evolveThrowsIllegalArgumentExceptionWhenSourceHasInvalidChar() {
//        se.setSource(sourceInvalidChar);
//        se.evolve(target);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void evolveThrowsIllegalArgumentExceptionWhenTargetAndSourceHaveInvalidChar() {
//        se.setSource(sourceInvalidChar);
//        se.evolve(targetInvalidChar);
//    }
}
