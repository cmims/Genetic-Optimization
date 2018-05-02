package com.slethron.geneticoptimization.problem.test;

import com.slethron.geneticoptimization.problem.StringMatchProblem;
import com.slethron.util.NanoTimer;
import com.slethron.util.RandomUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringMatchProblemTest {
    private static final NanoTimer NANO_TIMER = new NanoTimer();
    
    @Test
    void evolveToHelloWorld() {
        var target = "Hello, World!";
        var stringMatchProblem = new StringMatchProblem(target);
        var solution = stringMatchProblem.solve(10000, 100, .05);
        System.out.println(solution);
        assertEquals(target, solution);
    }
    
    @Test
    void evolveToSomeLargeString() {
        var target = RandomUtil.generateRandomString(100);
        var stringMatchProblem = new StringMatchProblem(target);
        var solution = stringMatchProblem.solve(10000, 1000, .05);
        System.out.println(solution);
        assertEquals(target, solution);
    }
}