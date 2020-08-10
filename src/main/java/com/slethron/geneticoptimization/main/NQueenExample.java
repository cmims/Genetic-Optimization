package com.slethron.geneticoptimization.main;

import com.slethron.geneticoptimization.problem.NQueensProblem;

public class NQueenExample {
    public static void main(String[] args) {
        var nQueensProblem = new NQueensProblem(20);
        var initialPopulation = nQueensProblem.generateInitialPopulation(1000);
        var solution = nQueensProblem.optimize(initialPopulation, .05, .20);

        System.out.println(solution);
        System.out.println(solution.draw('&', '_'));
    }
}
