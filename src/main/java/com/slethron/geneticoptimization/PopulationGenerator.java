package com.slethron.geneticoptimization;

import java.util.List;

public abstract class PopulationGenerator<E> {
    public abstract List<E> generatePopulation(int populationSize);
}
