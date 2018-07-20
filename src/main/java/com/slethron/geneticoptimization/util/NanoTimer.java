package com.slethron.geneticoptimization.util;

public class NanoTimer {
    private boolean isTimeRecorded;
    private boolean isStarted;
    private long start;
    private long stop;
    
    public void start() {
        if (isTimeRecorded) {
            isTimeRecorded = false;
        }
        start = System.nanoTime();
        isStarted = true;
    }
    
    public void stop() {
        if (isStarted) {
            stop = System.nanoTime();
            isStarted = false;
            isTimeRecorded = true;
        }
    }
    
    public void clear() {
        if (isTimeRecorded) {
            isTimeRecorded = false;
        }
    }
    
    public boolean isStarted() {
        return isStarted;
    }
    
    public String toString() {
        if (isTimeRecorded) {
            var exeTimeSec = (stop - start) / 1000000000L;
            var exeTimeMil = ((stop - start) % 1000000000L) / 1000000L;
            return exeTimeSec + "." + exeTimeMil + " seconds";
        } else {
            return "No time recorded.";
        }
    }
}
