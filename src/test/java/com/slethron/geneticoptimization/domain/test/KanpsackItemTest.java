package com.slethron.geneticoptimization.domain.test;

import com.slethron.geneticoptimization.domain.KnapsackItem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class KanpsackItemTest {
    
    @Test
    public void itemsOfSimilarValueAndWeightAreNotEqual() {
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
