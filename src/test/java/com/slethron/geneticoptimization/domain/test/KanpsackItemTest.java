package com.slethron.geneticoptimization.domain.test;

import com.slethron.geneticoptimization.domain.KnapsackItem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class KanpsackItemTest {
    
    @Test
    public void itemsOfSimilarValueAndWeightAreNotEqual() {
        var itemIdA = 0;
        var weightA = 10;
        var valueA = 5;
        var itemIdB = 1;
        var weightB = 8;
        var valueB = 7;
        
        var itemA = new KnapsackItem(itemIdA, weightA, valueA);
        var itemB = new KnapsackItem(itemIdB, weightB, valueB);
        
        assertEquals(itemIdA, itemA.getItemId());
        assertEquals(weightA, itemA.getWeight());
        assertEquals(valueA, itemA.getValue());
        assertEquals(itemIdB, itemB.getItemId());
        assertEquals(weightB, itemB.getWeight());
        assertEquals(valueB, itemB.getValue());
        
        assertNotEquals(itemA, itemB);
    }
}
