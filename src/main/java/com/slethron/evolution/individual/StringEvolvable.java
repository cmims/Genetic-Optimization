package com.slethron.evolution.individual;

import com.slethron.evolution.individual.interfaces.Evolvable;
import com.slethron.util.RandomUtil;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class StringEvolvable extends Evolvable<String> {
    private String target;
    private final StringBuilder sb = new StringBuilder();
    
    public StringEvolvable(String target) {
        this(target, RandomUtil.generateRandomString(target.length()));
    }
    
    public StringEvolvable(StringEvolvable stringEvolvable) {
        this(stringEvolvable.target, stringEvolvable.source());
    }
    
    public StringEvolvable(String target, String source) {
        super(source);
        this.target = target;
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
        var index = ThreadLocalRandom.current().nextInt(source().length());
        var mutation = ThreadLocalRandom.current().nextInt(99) < 5 ?
                ThreadLocalRandom.current().nextInt(32, 126) :
                source().charAt(index);
        
        return sb.delete(0, sb.length())
                .append(source(), 0, index)
                .append(Character.toChars(mutation))
                .append(source(), index + 1, source().length())
                .toString();
    }
    
    @Override
    public String toString() {
        return source().toString();
    }
    
    public static class StringEvolvableComparator implements Comparator<StringEvolvable> {
        @Override
        public int compare(StringEvolvable o1, StringEvolvable o2) {
            return Double.compare(o1.fitness(), o2.fitness());
        }
    }
}
