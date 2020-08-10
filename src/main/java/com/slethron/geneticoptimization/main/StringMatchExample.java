package com.slethron.geneticoptimization.main;

import com.slethron.geneticoptimization.problem.StringMatchProblem;
import com.slethron.geneticoptimization.util.RandomGeneratorUtil;

public class StringMatchExample {
    public static void main(String[] args) {
        var randomString = RandomGeneratorUtil.generateRandomString(15);

        System.out.println("Target String: " + randomString);

        var stringMatchProblem = new StringMatchProblem(randomString);
        var initialPopulation = stringMatchProblem.generateInitialPopulation(1000);
        var solution = stringMatchProblem.optimize(initialPopulation, .08, .25);

        System.out.println("Optimized String: " + solution);
    }
}
