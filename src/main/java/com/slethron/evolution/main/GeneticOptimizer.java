package com.slethron.evolution.main;

import com.slethron.evolution.individual.EvolvableNQueensBoard;
import com.slethron.evolution.individual.interfaces.Evolvable;
import com.slethron.evolution.population.interfaces.Population;

/**
 * 1) generate initial population of random n queens boards
 * 2) mutate each individual (mutate drives the goal-orientation)
 * 2) get the fitness of each and sort based on fitness
 * 3) loop through the first quarter-length of the element-array
 */
public class GeneticOptimizer {
    private GeneticOptimizer() { }
    public static Evolvable solve(Population population) {
        while (true) {
            
            // Mutate each individual, only accept beneficial mutations
            Evolvable target = null;
            for (Evolvable e : population.getPopulation()) {
                if ((target = e.next()) != null) {
                    // If next() finds a board that meets target, break
                    return target;
                }
            }
            
            population.nextGeneration();
        }
    }
}
