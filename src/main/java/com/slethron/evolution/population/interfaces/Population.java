package com.slethron.evolution.population.interfaces;

import com.slethron.evolution.individual.interfaces.Evolvable;

public interface Population<E extends Evolvable> {
    E[] generateInitialPopulation(int populationSize);
    E[] nextGeneration();
    E generateIndividualFromParents(E parentA, E parentB);
    E[] getPopulation();
    void setPopulation(E[] population);
}
