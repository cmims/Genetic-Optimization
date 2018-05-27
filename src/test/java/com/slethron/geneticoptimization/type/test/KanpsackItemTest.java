package com.slethron.geneticoptimization.type.test;

import com.slethron.geneticoptimization.type.KnapsackItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class KanpsackItemTest {
    
    @Test
    void itemsOfSimilarValueAndWeightAreNotEqual() {
        var weightA = 10;
        var valueA = 5;
        var weightB = 8;
        var valueB = 7;
        
        var itemA = new KnapsackItem(weightA, valueA);
        var itemB = new KnapsackItem(weightB, valueB);
        
        
        assertEquals(weightA, itemA.getWeight());
        assertEquals(valueA, itemA.getValue());
        assertEquals(weightB, itemB.getWeight());
        assertEquals(valueB, itemB.getValue());
        
        assertNotEquals(itemA, itemB);
    }
}
