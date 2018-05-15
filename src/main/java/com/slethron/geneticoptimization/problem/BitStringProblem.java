package com.slethron.geneticoptimization.problem;

import com.slethron.geneticoptimization.GeneticOptimizer;
import com.slethron.geneticoptimization.type.BitString;
import com.slethron.util.NanoTimer;
import com.slethron.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Objects.isNull;

public class BitStringProblem implements GeneticOptimizer<BitString> {
    private Random random;
    private int length;
    
    public BitStringProblem(int length) {
        random = new Random();
        this.length = length;
    }
    
    @Override
    public List<BitString> generateInitialPopulation(int populationSize) {
        var population = new ArrayList<BitString>();
        for (var i = 0; i < populationSize; i++) {
            population.add(RandomUtil.generateRandomBitString(length));
        }
        
        return population;
    }
    
    @Override
    public BitString generateIndividualFromParents(BitString parentA, BitString parentB) {
        var split = random.nextInt(parentA.length());
        var child = new BitString(parentA);
        for (var i = 0; i < parentA.length(); i++) {
            if (i > split) {
                child.set(i, parentB.get(i));
            }
        }
        
        return child;
    }
    
    @Override
    public BitString mutate(BitString individual, double mutationRate) {
        var mutated = new BitString(individual);
        for (var i = 0; i < mutated.length(); i++) {
            if (random.nextDouble() <= mutationRate) {
                mutated.set(i, !mutated.get(i));
            }
        }
        
        return mutated;
    }
    
    @Override
    public double fitness(BitString individual) {
        var fitVal = 0;
        for (var i = 0; i < individual.length(); i++) {
            if (!individual.get(i)) {
                fitVal++;
            }
        }
    
        return fitVal;
    }
    
    public static void main(String[] args) {
        var nanoTimer = new NanoTimer();
        
        var length = 50;
        var bitStringProblem = new BitStringProblem(length);
        
        nanoTimer.start();
        var solution = bitStringProblem.solve(10000, 1000, .05, .25);
        nanoTimer.stop();
        
        System.out.print("Solution: ");
        for (var i = 0; i < solution.length(); i++) {
            System.out.print(solution.get(i) ? "1" : "0");
        }
        System.out.println();
        
        System.out.println("Solution for length=" + length + " found in " + nanoTimer.toString());
    }
}
