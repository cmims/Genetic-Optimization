package com.slethron.evolution.population;

import com.slethron.evolution.individual.Evolvable;

public interface Population<E extends Evolvable> {
    Evolvable evolve();
    void generateInitialPopulation(int populationSize);
    void nextGeneration();
    E generateIndividualFromParents(E parentA, E parentB);
}
