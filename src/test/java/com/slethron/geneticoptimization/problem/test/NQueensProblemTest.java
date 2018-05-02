package com.slethron.geneticoptimization.problem.test;

import com.slethron.geneticoptimization.problem.NQueensProblem;
import com.slethron.util.NanoTimer;
import org.junit.jupiter.api.Test;

class NQueensProblemTest {
    private static final NanoTimer NANO_TIMER = new NanoTimer();
    
    @Test
    void solveFor12Queens() {
        var n = 12;
        var nQueensProblem = new NQueensProblem(n);
        
        NANO_TIMER.start();
        var solution = nQueensProblem.solve(10000, 1000, .06);
        NANO_TIMER.stop();
        
        System.out.println("Solution: " + solution);
        System.out.println("Solution for n=" + n + " found in " + NANO_TIMER.toString());
    }
}
