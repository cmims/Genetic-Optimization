package com.slethron.geneticoptimization.problem;

import com.slethron.geneticoptimization.DeterministicOptimizer;
import com.slethron.geneticoptimization.PopulationGenerator;
import com.slethron.geneticoptimization.domain.BitString;
import com.slethron.geneticoptimization.util.NanoTimer;
import com.slethron.geneticoptimization.util.RandomGeneratorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BitStringProblem extends PopulationGenerator<BitString> implements DeterministicOptimizer<BitString> {
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
            population.add(RandomGeneratorUtil.generateRandomBitString(length));
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
        
        var population = bitStringProblem.generateInitialPopulation(10000);

        nanoTimer.start();
        var solution = bitStringProblem.optimize(population, .05, .25);
        nanoTimer.stop();
        
        System.out.print("Solution: ");
        for (var i = 0; i < solution.length(); i++) {
            System.out.print(solution.get(i) ? "1" : "0");
        }
        System.out.println();
        
        System.out.println("Solution for length=" + length + " found in " + nanoTimer.toString());
    }
}
