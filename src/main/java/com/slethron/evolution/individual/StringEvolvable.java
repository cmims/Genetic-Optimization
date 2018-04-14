package com.slethron.evolution.individual;

import com.slethron.evolution.individual.interfaces.Evolvable;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class StringEvolvable extends Evolvable<String> {
    private String target;
    private final StringBuilder sb;
    
    public StringEvolvable(String target) {
        this(generateRandomString(target.length()), target);
    }

    private StringEvolvable(String source, String target) {
        super(source);
        this.target = target;
        sb = new StringBuilder();
    }
    
    @Override
    public double fitness() {
        double fitVal = 0;
        for (var i = 0; i < source().length(); i++) {
            fitVal += Math.abs(target.charAt(i) - source().charAt(i));
        }

        return fitVal;
    }

    @Override
    public String mutate() {
        var i = ThreadLocalRandom.current().nextInt(source().length());
        var flip = ThreadLocalRandom.current().nextBoolean() ? 1 : -1;
        var mutation = source().charAt(i) + flip;

        if (mutation > 126) {
            mutation = 32;
        } else if (mutation < 32){
            mutation = 126;
        }

        return sb.delete(0, sb.length())
                .append(source(), 0, i)
                .append((char) mutation)
                .append(source(), i + 1, source().length())
                .toString();
    }
    
    private static String generateRandomString(int length) {
        var sb = new StringBuilder();
        for (var i = 0; i < length; i++) {
            char c = (char) (ThreadLocalRandom.current().nextInt(126 - 32) + 32);
            sb.append(c);
        }
        
        return sb.toString();
    }
    
    public static class StringEvolvableComparator implements Comparator<StringEvolvable> {
        @Override
        public int compare(StringEvolvable o1, StringEvolvable o2) {
            return Double.compare(o2.fitness(), o1.fitness());
        }
    }
}
