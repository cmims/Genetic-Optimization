package com.slethron.geneticoptimization.problem;

import com.slethron.geneticoptimization.GeneticOptimizer;
import com.slethron.util.RandomUtil;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringMatchProblem implements GeneticOptimizer<String> {
    private String target;
    
    public StringMatchProblem(String target) {
        this.target = target;
    }
    
    @Override
    public List<String> generateInitialPopulation(int populationSize) {
        return IntStream.range(0, populationSize)
                .unordered().parallel()
                .mapToObj(i -> RandomUtil.generateRandomString(target.length()))
                .collect(Collectors.toList());
    }
    
    @Override
    public String generateIndividualFromParents(String parentA, String parentB) {
        var split = new AtomicInteger(ThreadLocalRandom.current().nextInt(parentA.length()));
        return IntStream.range(0, parentA.length())
                .unordered().parallel()
                .mapToObj(i -> i < split.get() ? parentA.charAt(i) : parentB.charAt(i))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
    
    @Override
    public String mutate(String individual) {
        var index = ThreadLocalRandom.current().nextInt(individual.length());
        var mutation = ThreadLocalRandom.current().nextInt(32, 127);
        return new StringBuilder()
                .append(individual, 0, index)
                .append(Character.toChars(mutation))
                .append(individual, index + 1, individual.length())
                .toString();
    }
    
    @Override
    public double fitness(String individual) {
        return IntStream.range(0, individual.length())
                .unordered().parallel()
                .mapToDouble(i -> Math.abs(target.charAt(i) - individual.charAt(i)))
                .sum();
    }
}
