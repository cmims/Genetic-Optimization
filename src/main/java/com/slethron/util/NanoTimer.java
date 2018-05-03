package com.slethron.util;

public class NanoTimer {
    private boolean isTimeRecorded;
    private boolean isStarted;
    private long pre;
    private long post;
    
    public void start() {
        if (isTimeRecorded) {
            isTimeRecorded = false;
        }
        pre = System.nanoTime();
        isStarted = true;
    }
    
    public void stop() {
        if (isStarted) {
            post = System.nanoTime();
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
            var exeTimeSec = (post - pre) / 1000000000L;
            var exeTimeMil = ((post - pre) % 1000000000L) / 1000000L;
            return exeTimeSec + "." + exeTimeMil + " s";
        } else {
            return "No time recorded.";
        }
    }
}
