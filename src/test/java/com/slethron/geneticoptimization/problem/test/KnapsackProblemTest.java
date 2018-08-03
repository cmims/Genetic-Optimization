package com.slethron.geneticoptimization.problem.test;

import com.slethron.geneticoptimization.domain.Knapsack;
import com.slethron.geneticoptimization.problem.KnapsackProblem;
import com.slethron.geneticoptimization.util.RandomUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KnapsackProblemTest {
    private int maxWeight;
    private KnapsackProblem knapsackProblem;
    private List<Knapsack.KnapsackItem> items;
    
    @Before
    public void beforeEach() {
        maxWeight = 40;
        items = new ArrayList<>();
        var weightsOfItems = new int[]{10, 5, 24, 6, 4, 3, 8, 9, 12, 13, 8, 1, 1, 2, 4, 2};
        var valuesOfItems = new int[]{24, 80, 50, 4, 15, 20, 19, 50, 60, 80, 5, 12, 16, 18, 21, 5};
        for (var i = 0; i < weightsOfItems.length; i++) {
            items.add(new Knapsack.KnapsackItem(i, weightsOfItems[i], valuesOfItems[i]));
        }
        knapsackProblem = new KnapsackProblem(maxWeight, items);
    }

    @Test
    public void generatePopulationOfSize100ContainingKnapsacksOfMaxWeight200() {
        var size = 100;
        var population = knapsackProblem.generatePopulation(size);
        
        assertEquals(size, population.size());
        for (var knapsack : population) {
            for (var item : knapsack.getItems()) {
                assertTrue(items.contains(item));
            }
        }
    }
    
    @Test
    public void generateIndividualFromParentsGeneratesChildThatHasElementsFromOneOrBothParents() {
        var parentA = RandomUtil.generateRandomKnapsack(maxWeight, items);
        var parentB = RandomUtil.generateRandomKnapsack(maxWeight, items);
        
        var child = knapsackProblem.generateIndividualFromParents(parentA, parentB);
        
        assertEquals(maxWeight, child.getMaxWeight());
        for (var item : child.getItems()) {
            assertTrue(parentA.getItems().contains(item) || parentB.getItems().contains(item));
        }
    }
}
