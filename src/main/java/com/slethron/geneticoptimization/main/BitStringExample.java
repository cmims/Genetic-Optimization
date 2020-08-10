package com.slethron.geneticoptimization.main;

import com.slethron.geneticoptimization.problem.BitStringProblem;

public class BitStringExample {
    public static void main(String[] args) {
        var bitStringProblem = new BitStringProblem(20);
        var initialPopulation = bitStringProblem.generateInitialPopulation(1000);
        var solution = bitStringProblem.optimize(initialPopulation, .08, .25);

        System.out.println(solution);
    }
}
