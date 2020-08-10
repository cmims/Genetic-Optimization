package com.slethron.geneticoptimization.main;

import com.slethron.geneticoptimization.domain.Knapsack;
import com.slethron.geneticoptimization.problem.KnapsackProblem;

import java.util.ArrayList;

public class KnapsackExample {
    public static void main(String[] args) {
        // Create items for the knapsacks
        var items = new ArrayList<Knapsack.KnapsackItem>() {{
            add(new Knapsack.KnapsackItem(0, 5, 10));
            add(new Knapsack.KnapsackItem(1, 4, 40));
            add(new Knapsack.KnapsackItem(2, 6, 30));
            add(new Knapsack.KnapsackItem(3, 3, 50));
        }};

        var knapsackProblem = new KnapsackProblem(10, items);
        var initialPopulation = knapsackProblem.generateInitialPopulation(1000);
        var likelySolution = knapsackProblem.optimize(initialPopulation, 1000, .08, .25)
                .get(0);

        System.out.println(likelySolution);
    }
}
