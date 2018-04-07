package com.slethron.evolution.individual;

import com.slethron.evolution.individual.interfaces.Evolvable;

import java.util.Random;

public class EvolvableString extends Evolvable<String> {
    private final StringBuilder sb;
    private final Random random;
    private String target;

    public EvolvableString(String source, String target) {
        super(source);
        sb = new StringBuilder();
        random = new Random();
        this.target = target;
    }
    
    @Override
    public double fitness(String source) {
        double fitVal = 0;
        for (var i = 0; i < source.length(); i++) {
            fitVal += Math.abs(target.charAt(i) - source.charAt(i));
        }

        return fitVal;
    }

    @Override
    public String mutate(String source) {
        var i = random.nextInt(source.length());
        var flip = random.nextBoolean() ? 1 : -1;
        var mutation = source.charAt(i) + flip;

        if (mutation > 126) {
            mutation = 32;
        } else if (mutation < 32){
            mutation = 126;
        }

        return sb.delete(0, sb.length())
                .append(source, 0, i)
                .append(Character.toChars(mutation))
                .append(source, i + 1, source.length())
                .toString();
    }
    
    public String getTarget() {
        return target;
    }
    
    public void setTarget(String target) {
        this.target = target;
    }
}
