package com.slethron.geneticoptimization.problem;

import com.slethron.geneticoptimization.GeneticOptimizer;
import com.slethron.geneticoptimization.domain.Knapsack;
import com.slethron.geneticoptimization.domain.KnapsackItem;
import com.slethron.util.NanoTimer;
import com.slethron.util.RandomUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KnapsackProblem implements GeneticOptimizer<Knapsack> {
    private Random random;
    private int maxWeight;
    private List<KnapsackItem> itemsToPut;
    
    public KnapsackProblem(int maxWeight, int[] weightsOfItems, int[] valuesOfItems) {
        random = new Random();
        this.maxWeight = maxWeight;
        itemsToPut = new ArrayList<>();
        for (var i = 0; i < weightsOfItems.length; i++) {
            itemsToPut.add(new KnapsackItem(weightsOfItems[i], valuesOfItems[i]));
        }
    }
    
    @Override
    public Knapsack solve(int populationSize, int generationLimit, double mutationRate, double fittestSampleRatio) {
        random = new Random();
        var population = generateInitialPopulation(populationSize);
        
        for (var generation = 0; generation < generationLimit; generation++) {
            var p = population;
            population = population.parallelStream()
                    .map(individual -> {
                        var sampleBound = (int) Math.round(populationSize * fittestSampleRatio);
                        var child = generateIndividualFromParents(
                                p.get(random.nextInt(sampleBound)), p.get(random.nextInt(sampleBound))
                        );
                        
                        child = mutate(child, mutationRate);
                        
                        return child;
                    }).sorted(Comparator.comparingDouble(this::fitness).reversed())
                    .collect(Collectors.toList());
        }
        
        return population.get(0);
    }
    
    @Override
    public List<Knapsack> generateInitialPopulation(int populationSize) {
        return IntStream.range(0, populationSize)
                .parallel().unordered()
                .mapToObj(knapsack -> RandomUtil.generateRandomKnapsack(maxWeight, itemsToPut))
                .collect(Collectors.toList());
    }
    
    @Override
    public Knapsack generateIndividualFromParents(Knapsack parentA, Knapsack parentB) {
        var child = new Knapsack(maxWeight);
        var itemsFromParents = new ArrayList<>(parentA.getItems());
        for (var item : parentB.getItems()) {
            if (!itemsFromParents.contains(item)) {
                itemsFromParents.add(item);
            }
            
        }
        
        var success = true;
        while (success) {
            if (itemsFromParents.isEmpty()) {
                break;
            }
            var item = itemsFromParents.get(random.nextInt(itemsFromParents.size()));
            success = child.put(item);
            itemsFromParents.remove(item);
        }
        
        if (!itemsFromParents.isEmpty()) {
            itemsFromParents.sort(Comparator.comparingDouble(KnapsackItem::getValue));
            
            for (KnapsackItem item : itemsFromParents) {
                child.put(item);
            }
        }
        
        return child;
    }
    
    @Override
    public Knapsack mutate(Knapsack individual, double mutationRate) {
        var mutated = new Knapsack(individual);
        for (KnapsackItem item : individual.getItems()) {
            if (random.nextDouble() <= mutationRate) {
                while (true) {
                    var replacementItem = itemsToPut.get(random.nextInt(itemsToPut.size()));
                    if (!mutated.getItems().contains(replacementItem)) {
                        mutated.remove(item);
                        mutated.put(replacementItem);
                        break;
                    }
                }
            }
        }
        
        return mutated;
    }
    
    @Override
    public double fitness(Knapsack individual) {
        return individual.getTotalValue();
    }
    
    public List<KnapsackItem> getItemsToPut() {
        return itemsToPut;
    }
    
    public static void main(String[] args) {
        var nanoTimer = new NanoTimer();
        
        var maxWeight = 48;
        var knapsackProblem = new KnapsackProblem(maxWeight,
                new int[]{10, 15, 25, 15, 6, 4, 2, 1, 5, 12, 18},
                new int[]{14, 17, 10, 8, 3, 10, 51, 9, 25, 6, 9});
        
        var itemsToPut = knapsackProblem.getItemsToPut();
        for (var item : itemsToPut) {
            System.out.println(item);
        }
        
        nanoTimer.start();
        var solution = knapsackProblem.solve(1000, 1000, .06, .25);
        nanoTimer.stop();
        
        var itemsInContainer = solution.getItems();
        for (KnapsackItem item : itemsInContainer) {
            System.out.println(item);
        }
        
        System.out.println("Solution for maxWeight=" + maxWeight + " and selected items found in "
                + nanoTimer.toString());
        System.out.println("Fitness of solution is: " + knapsackProblem.fitness(solution));
        System.out.println(solution);
    }
}
