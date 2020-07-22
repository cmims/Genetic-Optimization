package com.slethron.geneticoptimization.domain.test;

import com.slethron.geneticoptimization.domain.Knapsack;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class KnapsackTest {
    @Test
    void putFailsWhenAttemptingToPutItemInBagToExceedMaxWeight() {
        var knapsack = new Knapsack(5);
        var itemTooBig = new Knapsack.KnapsackItem(10, 10, 10);
        var item = new Knapsack.KnapsackItem(5, 5, 5);
        
        assertFalse(knapsack.put(itemTooBig));
        assertTrue(knapsack.put(item));
    }
    
    @Test
    void getItemsReturnsListOfItemsPlacedInTheKnapsack() {
        var random = new Random();
        var itemsToPut = new ArrayList<Knapsack.KnapsackItem>();
        for (var i = 0; i < 10; i++) {
            itemsToPut.add(new Knapsack.KnapsackItem(i, random.nextInt(10), random.nextInt(10)));
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
        var item1 = new Knapsack.KnapsackItem(0, 10, 5);
        var item2 = new Knapsack.KnapsackItem(1, 10, 5);
        
        var knapsack = new Knapsack(40);
        knapsack.put(item1);
        knapsack.put(item2);
        assertEquals(item1.getWeight() + item2.getWeight(), knapsack.getTotalWeight());
        assertEquals(item1.getValue() + item2.getValue(), knapsack.getTotalValue());
    }
    
    @Test
    void equalsTest() {
        var knapsack = new Knapsack(10);
        var knapsack2 = new Knapsack(10);
        
        var knapsackItem1 = new Knapsack.KnapsackItem(0, 4, 2);
        var knapsackItem2 = new Knapsack.KnapsackItem(1, 4, 1);
        
        knapsack.put(knapsackItem1);
        knapsack.put(knapsackItem2);
        
        knapsack2.put(knapsackItem1);
        knapsack2.put(knapsackItem2);
        
        assertEquals(knapsack, knapsack2);
        
        knapsack.put(new Knapsack.KnapsackItem(2, 2,4));
        
        assertNotEquals(knapsack, knapsack2);
    }
}
