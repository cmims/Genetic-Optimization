package com.slethron.geneticoptimization.type.test;

import com.slethron.geneticoptimization.type.Knapsack;
import com.slethron.geneticoptimization.type.KnapsackItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KnapsackTest {
    
    @Test
    void putFailsWhenItemToBig() {
        var knapsack = new Knapsack(5);
        var itemTooBig = new KnapsackItem(10, 10);
        var item = new KnapsackItem(5, 5);
        
        
        assertFalse(knapsack.put(itemTooBig));
        assertTrue(knapsack.put(item));
    }
    
    @Test
    void getItemsReturnsEqualListOfItems() {
        var random = new Random();
        var itemsToPut = new ArrayList<KnapsackItem>();
        for (var i = 0; i < 10; i++) {
            itemsToPut.add(new KnapsackItem(
                    random.nextInt(10), random.nextInt(10)
            ));
        }
        
        var knapsack = new Knapsack(20);
        for (var item : itemsToPut) {
            if (!knapsack.put(item)) {
                break;
            }
        }
        
        for (var item : knapsack.getItems()) {
            assertTrue(itemsToPut.contains(item));
        }
    }
    
    @Test
    void getTotalWeightAndGetTotalValueAreAccurate() {
        var item1 = new KnapsackItem(10, 5);
        var item2 = new KnapsackItem(10, 5);
        
        var knapsack = new Knapsack(40);
        knapsack.put(item1);
        knapsack.put(item2);
        assertEquals(item1.getWeight() + item2.getWeight(), knapsack.getTotalWeight());
        assertEquals(item1.getValue() + item2.getValue(), knapsack.getTotalValue());
    }
}
