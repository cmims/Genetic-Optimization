package com.slethron.geneticoptimization.domain.test;

import com.slethron.geneticoptimization.domain.Knapsack;
import com.slethron.geneticoptimization.domain.KnapsackItem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class KnapsackTest {
    
    @Test
    public void putFailsWhenAttemptingToPutItemInBagToExceedMaxWeight() {
        var knapsack = new Knapsack(5);
        var itemTooBig = new KnapsackItem(10, 10);
        var item = new KnapsackItem(5, 5);
        
        assertFalse(knapsack.put(itemTooBig));
        assertTrue(knapsack.put(item));
    }
    
    @Test
    public void getItemsReturnsListOfItemsPlacedInTheKnapsack() {
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
    public void getTotalWeightAndGetTotalValueAreAccurate() {
        var item1 = new KnapsackItem(10, 5);
        var item2 = new KnapsackItem(10, 5);
        
        var knapsack = new Knapsack(40);
        knapsack.put(item1);
        knapsack.put(item2);
        assertEquals(item1.getWeight() + item2.getWeight(), knapsack.getTotalWeight());
        assertEquals(item1.getValue() + item2.getValue(), knapsack.getTotalValue());
    }
    
    @Test
    public void equalsReturnsTrueForKnapsacksThatAreTheSameAndFalseForKnapsacksThatArent() {
        var knapsack = new Knapsack(10);
        var knapsack2 = new Knapsack(10);
        
        var knapsackItem1 = new KnapsackItem(4, 2);
        var knapsackItem2 = new KnapsackItem(4, 1);
        
        knapsack.put(knapsackItem1);
        knapsack.put(knapsackItem2);
        
        knapsack2.put(knapsackItem1);
        knapsack2.put(knapsackItem2);
        
        assertEquals(knapsack, knapsack2);
        
        knapsack.put(new KnapsackItem(2,4));
        
        assertNotEquals(knapsack, knapsack2);
    }
}
