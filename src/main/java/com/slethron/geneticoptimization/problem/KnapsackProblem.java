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
    
    public KnapsackProblem(int maxWeight, List<KnapsackItem> itemsToPut) {
        random = new Random();
        this.maxWeight = maxWeight;
        this.itemsToPut = itemsToPut;
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
        var items = new ArrayList<>(parentA.getItems());
        for (var item : parentB.getItems()) {
            if (!items.contains(item)) {
                items.add(item);
            }
        }
        
        while (!items.isEmpty()) {
            var itemToPut = items.get(random.nextInt(items.size()));
            child.put(itemToPut);
            items.remove(itemToPut);
        }
        
        return child;
    }
    
    @Override
    public Knapsack mutate(Knapsack individual, double mutationRate) {
        var items = new ArrayList<>(itemsToPut);
        var mutated = new Knapsack(individual);
        for (var i = mutated.getItems().size(); i >= 1; i--) {
            if (random.nextDouble() <= mutationRate) {
                while (true) {
                    var replacementItem = items.get(random.nextInt(items.size()));
                    if (!mutated.getItems().contains(replacementItem)) {
                        mutated.getItems().remove(i - 1);
                        if (mutated.put(replacementItem)) {
                            break;
                        }
                        return individual;
                    }
                    items.remove(replacementItem);
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
        var numberOfItems = 10;
        var maxItemWeight = 50;
        var random = new Random();
        var nanoTimer = new NanoTimer();

        var itemsToPut = IntStream.range(0, numberOfItems)
                .mapToObj(i -> new KnapsackItem(random.nextInt(maxItemWeight), random.nextInt(maxItemWeight)))
                .collect(Collectors.toList());

        for (var item : itemsToPut) {
            System.out.println(item);
        }

        var maxWeight = random.nextInt(100) + 50;
        var knapsackProblem = new KnapsackProblem(maxWeight, itemsToPut);
        
        nanoTimer.start();
        var solution = knapsackProblem.solve(1000, 1000, .06, .25);
        nanoTimer.stop();
        
        System.out.println("Solution for maxWeight=" + maxWeight + " and selected items found in "
                + nanoTimer.toString());
        System.out.println("Fitness of solution is: " + knapsackProblem.fitness(solution));
        System.out.println(solution);
    }
}
