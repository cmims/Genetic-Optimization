package com.slethron.util.test;

import com.slethron.util.NanoTimer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NanoTimerTest {
    
    @Test
    public void verifyRunTimerAndSleepFor1SecondOnMainThread() throws InterruptedException {
        var timer = new NanoTimer();
        timer.start();
        Thread.sleep(1000);
        timer.stop();
        
        assertEquals("1", String.valueOf(timer.toString().charAt(0)));
    }
    
    @Test
    public void testingOfVariousScenarios() throws InterruptedException {
        var timer = new NanoTimer();
        /* Get the error string returned by timer for later use */
        var timerError = timer.toString();
        /* Stopping the timer before starting it should not cause an error */
        timer.stop();
        assertFalse(timer.isStarted());
        /* Start the timer */
        timer.start();
        assertTrue(timer.isStarted());
        /* Starting the timer again should not cause an error but it overwrites
        the time it was called previously */
        timer.start();
        assertTrue(timer.isStarted());
        /* Clearing the timer after it is started should not cause an error,
        although it does not actually perform any function while this is the case */
        timer.clear();
        assertTrue(timer.isStarted());
        /* Clearing the timer a second time should not cause an error */
        timer.clear();
        assertTrue(timer.isStarted());
        /* Sleep the main thread for a second in order to assert against the timer */
        Thread.sleep(1000);
        /* Stop the timer */
        timer.stop();
        assertFalse(timer.isStarted());
        /* Assert the timer has recorded 1 second, ignore milliseconds */
        assertEquals("1", String.valueOf(timer.toString().charAt(0)));
        /* Stopping the timer again should not cause an error */
        timer.stop();
        assertFalse(timer.isStarted());
        /* Clear the recorded time */
        timer.clear();
        assertEquals(timerError, timer.toString());
    }
}
