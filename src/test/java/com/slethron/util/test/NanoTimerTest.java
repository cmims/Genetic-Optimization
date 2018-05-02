package com.slethron.util.test;

import com.slethron.util.NanoTimer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NanoTimerTest {
    @Test
    void runTimerOnMainThreadAndSleepFor1Second() throws InterruptedException {
        var timer = new NanoTimer();
        timer.start();
        Thread.sleep(1000);
        timer.stop();
        
        assertEquals("1", String.valueOf(timer.toString().charAt(0)));
    }
}
