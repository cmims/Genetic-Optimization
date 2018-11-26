package com.slethron.geneticoptimization;

import java.util.Comparator;
import java.util.List;
import java.util.SplittableRandom;
import java.util.stream.Collectors;

public interface DeterministicOptimizer<E> extends GeneticOptimizer<E>{
    default E optimize(List<E> population, double mutationRate, double fittestSampleRatio) {
        var random = new SplittableRandom();
        while (fitness(population.get(0)) != 0) {
            var p = population;
            population = p.parallelStream()
                    .map(individual -> {
                        var sampleBound = (int) Math.rint(p.size() * fittestSampleRatio);
                        var child = generateIndividualFromParents(
                                p.get(random.nextInt(sampleBound)), p.get(random.nextInt(sampleBound)));

                        child = mutate(child, mutationRate);

                        return child;
                    }).sorted(Comparator.comparingDouble(this::fitness))
                    .collect(Collectors.toList());
        }

        return population.get(0);
    }
}
