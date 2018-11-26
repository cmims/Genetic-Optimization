<H3>Genetic Optimization</H3>
<p>
    Genetic Optimization is the processes of using random genetic processes to find more desirable solutions for
    optimization problems. This can be done in many ways, but the most common (used in this project) can be illustrated
    as a series of repeatedly executed phases:
</p>

1. Population Definition: Define the set of individuals (known as the population), that will be optimized. This can be a
predefined set of individuals, a set from a previously optimized population, or generated randomly.
1. Fitness Evaluation: Score each individual by evaluating their fitness values.
1. Natural Selection: Define a subset of the population that represents the individuals with the best fitness values.
1. Reproduction: Generate a new population of child individuals by repeatedly selecting two parent individuals randomly
from the <i>best fitness</i> subset and reproducing them. The children will express features of one or both parents.
1. Mutation: Allow the chance for each child's genetics to be mutated. It should be possible for a child to experience
multiple mutations that are either harmful or beneficial to the individual's fitness value.
1. Repeat the process with the resulting population. The process can be executed an undefined number of times (stochastic
optimization) or until the desired result is obtained (deterministic optimization).

<H5>Deterministic vs Stochastic Optimization</H5>

<p>
    <i>Deterministic Optimization</i> techniques define an initial set of parameters which are used to optimize a
    population until an individual is found which completely satisifies the given criteria. So the process only needs to
    be executed for as long as it takes to find that individual. <i>Stochastic Optimization</i> techniques usually rely
    on some randomness in the process to generate individuals with better fitness values. The process is essentially the
    same as in deterministic optimization, but the initial parameters may be more difficult to define, and the <i>best
    fitness</i> individual may not be immediately apparent.
</p>
<p>
    With either technique, the process can be executed as many times as is necessary to observe convergence.
    <i>Convergence</i> means there are very minute differences between individuals of the same generation and very
    little change per generation in genetic diversity.
</p>
<p>
    Of the below problems, the knapsack problem is the only one in this project that was implemented with a stochastic
    approach. The other problems are implemented with a derterministic approach. The difference can be seen in the
    problem definition. In the deterministic optimization problems, a single value is being minimized, such as the
    number of conflicts between pieces on a board or difference of a given character versus a target character in a
    string. Most of the parameters are nown beforehand. In these problems exist an apparent <i>best fitness</i>
    individual.
</p>
<p>
    By contrast, the knapsack problem needs maximization of two separate parameters, a knapsack's total weight and total
    value. The weight must be maximized but maintained under the maximum knapsack weight limit, while the value is
    maximized to find the best individual. When considering these factors beforehand, it's not possible to know the
    exact outcome of the optimization.
</p>
<p>
    Other differences can be seen in how the methods for fitness, mutation and parent reproduction work. With the
    deterministic problems, mutation sometimes only involves a simple flip of a bit, or random value replacement of an
    integer. There are no other rules by which those algorithms need to adhere in order to generate a valid invidual.
    Whereas with the knapsack problem, choosing a random item to remove from the knapsack and another random item to put
    into the knapsack often results in an illegal transaction. The optimization relies on the randomness of the order of
    insertion of items when reproducing parents to generate a child, as well as the mutation function possibly inserting
    an item into a bag with a better value, but weight equal to or lower than the removed item or which is still in the
    confines of the maximum-knapsack-weight after the transaction occurs.
</p>

___
<H5>Implemented Problems</H5>

<p>
    The problems implemented in this example project are listed below:
</p>

- Bit-String Problem
- Knapsack Problem
- N-Queens Problem
- String-Match Problem
- Sudoku

___
<H6>Terms</H6>

- <i>Fitness</i> - a non-complex, non-arbitrary value with no unit that represents the individuals fitness score.
- <i>Parent</i> - an individual from the population that has been reproduced with another individual from the same
generation to produce a child individual for the next generation.
- <i>Child</i> - an individual that has been produced from the reproduction of two parents from the previous generation;
expresses features of one or both parents.
- <i>Generation</i> - set of all the individuals generated by the previous population during reproduction
- <i>Reproduction</i> - the process of generating a new child from two parents and transferring characteristics to it
- <i>Mutation</i> - the process of changing characteristics of the child randomly during reproduction
- <i>Natural Selection</i> - describes the random process that selects the surviving individuals of each generation
(the individuals that will live to reproduce)
- <i>Convergence</i> - very minute differences between individuals of the same generation and very little
change per generation in genetic diversity can be observed during the process

___
<H6>src</H6>

- https://www.electricmonk.nl/log/2011/09/28/evolutionary-algorithm-evolving-hello-world/
- https://neos-guide.org/optimization-tree