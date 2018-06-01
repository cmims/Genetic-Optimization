package com.slethron.geneticoptimization.problem.test;

import com.slethron.geneticoptimization.domain.KnapsackItem;
import com.slethron.geneticoptimization.problem.KnapsackProblem;
import com.slethron.util.RandomUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnapsackProblemTest {
    private int maxWeight;
    private KnapsackProblem knapsackProblem;
    private List<KnapsackItem> items;
    
    @BeforeEach
    public void beforeEach() {
        maxWeight = 60;
        var weightsOfItems = new int[] { 10, 5, 24, 6, 4, 3, 8, 9, 12, 13, 8, 1, 1, 2, 4, 2 };
        var valuesOfItems = new int[] { 24, 80, 50, 4, 15, 20, 19, 50, 60, 80, 5, 12, 16, 18, 21, 5 };
        knapsackProblem = new KnapsackProblem(maxWeight, weightsOfItems, valuesOfItems);
        items = knapsackProblem.getItemsToPut();
    }
    
    @Test
    public void generateInitialPopulationOfSize100OfKnapsacksOfMaxWeight200() {
        var size = 100;
        var population = knapsackProblem.generateInitialPopulation(size);
        
        assertEquals(size, population.size());
        for (var knapsack : population) {
            for (var item : knapsack.getItems()) {
                assertTrue(items.contains(item));
            }
        }
    }
    
    @Test
    void generateIndividualFromParentsGeneratesChildThatHasElementsFromOneOrBothParents() {
        var parentA = RandomUtil.generateRandomKnapsack(maxWeight, items);
        var parentB = RandomUtil.generateRandomKnapsack(maxWeight, items);
        
        var child = knapsackProblem.generateIndividualFromParents(parentA, parentB);
        
        assertEquals(maxWeight, child.getMaxWeight());
        for (var i = 0; i < child.getItems().size(); i++) {
            assertTrue(child.getItems().get(i) == parentA.getItems().get(i)
                    || child.getItems().get(i) == parentB.getItems().get(i));
        }
    }
}
