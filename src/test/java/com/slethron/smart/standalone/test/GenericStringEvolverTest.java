package com.slethron.smart.standalone.test;

import com.slethron.smart.standalone.GenericStringEvolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GenericStringEvolverTest {
    private GenericStringEvolver se;
    private String source = "!dlroW ,olleH";
    private String target = "Hello, World!";
    private String sourceInvalidChar = "!dlroW ,®lleH";
    private String targetInvalidChar = "Hello, World®";
    private String targetInvalidLength = "Hello, World! ";
    
    @Test
    void evolveEvolvesSourceStringIntoTargetString() {
        se = new GenericStringEvolver(source);
        se.evolve(target);
        assertEquals(target, se.getSource());
    }
    
    @Test
    void evolveThrowsIllegalArgumentExceptionWhenTargetLengthNotEqualsSourceLength() {
        se = new GenericStringEvolver(source);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    se.evolve(targetInvalidLength);
                }
        );
    }
    
    @Test
    void evolveThrowsIllegalArgumentExceptionWhenTargetHasInvalidChar() {
        se = new GenericStringEvolver(source);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    se.evolve(targetInvalidChar);
                }
        );
    }
    
    @Test
    void evolveThrowsIllegalArgumentExceptionWhenSourceHasInvalidChar() {
        se = new GenericStringEvolver(sourceInvalidChar);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    se.evolve(target);
                }
        );
    }
    
    @Test
    void evolveThrowsIllegalArgumentExceptionWhenTargetAndSourceHaveInvalidChar() {
        se = new GenericStringEvolver(sourceInvalidChar);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    se.evolve(targetInvalidChar);
                }
        );
    }
}
