package com.slethron.geneticoptimization.problem;

import com.slethron.geneticoptimization.GeneticOptimizer;
import com.slethron.geneticoptimization.PopulationGenerator;
import com.slethron.geneticoptimization.domain.Knapsack;
import com.slethron.geneticoptimization.util.NanoTimer;
import com.slethron.geneticoptimization.util.RandomUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KnapsackProblem extends PopulationGenerator<Knapsack> implements GeneticOptimizer<Knapsack> {
    private Random random;
    private int maxWeight;
    private List<Knapsack.KnapsackItem> itemsToPut;
    
    public KnapsackProblem(int maxWeight, List<Knapsack.KnapsackItem> itemsToPut) {
        random = new Random();
        this.maxWeight = maxWeight;
        this.itemsToPut = itemsToPut;
    }
    
    @Override
    public Knapsack optimize(List<Knapsack> population, int generationLimit, double mutationRate, double fittestSampleRatio) {
        for (var generation = 0; generation < generationLimit; generation++) {
            var p = population;
            population = population.parallelStream()
                    .map(individual -> {
                        var sampleBound = (int) Math.round(p.size() * fittestSampleRatio);
                        var child = generateIndividualFromParents(
                                p.get(ThreadLocalRandom.current().nextInt(sampleBound)),
                                p.get(ThreadLocalRandom.current().nextInt(sampleBound)));
                        
                        child = mutate(child, mutationRate);
                        
                        return child;
                    }).sorted(Comparator.comparingDouble(this::fitness).reversed())
                    .collect(Collectors.toList());
        }
        
        return population.get(0);
    }
    
    @Override
    public List<Knapsack> generatePopulation(int populationSize) {
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
    
    public static void main(String[] args) {
        var numberOfItems = 10;
        var maxItemWeightValue = 50;
        var random = new Random();
        var nanoTimer = new NanoTimer();
        
        var itemsToPut = IntStream.range(0, numberOfItems)
                .mapToObj(i -> new Knapsack.KnapsackItem(i, random.nextInt(maxItemWeightValue), random.nextInt(maxItemWeightValue)))
                .collect(Collectors.toList());
        
        for (var item : itemsToPut) {
            System.out.println(item);
        }
        
        var maxWeight = random.nextInt(100) + 50;
        var knapsackProblem = new KnapsackProblem(maxWeight, itemsToPut);
        
        nanoTimer.start();
        var population = knapsackProblem.generatePopulation(10000);
        var solution = knapsackProblem.optimize(population, 1000, .06, .25);
        nanoTimer.stop();
        
        System.out.println("Solution for maxWeight=" + maxWeight + " and selected items found in "
                + nanoTimer.toString());
        System.out.println("Fitness of solution is: " + knapsackProblem.fitness(solution));
        System.out.println(solution);
    }
}
