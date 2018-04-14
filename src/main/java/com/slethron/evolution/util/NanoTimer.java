package com.slethron.evolution.util;

public class NanoTimer {
    private long pre;
    private long post;
    
    public void start() {
        pre = System.nanoTime();
    }
    
    public void stop() {
        post = System.nanoTime();
    }
    
    public String toString() {
        var exeTimeSec = (post - pre) / 1000000000L;
        var exeTimeMil = ((post - pre) % 1000000000L) / 1000000L;
        return String.valueOf(exeTimeSec) + "." + exeTimeMil + " s";
    }
}
