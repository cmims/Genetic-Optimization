package com.slethron.evolution;

import com.slethron.evolution.interfaces.Evolvable;

import java.util.Random;

public class EvolvableString extends Evolvable<String> {
    private StringBuilder sb;
    private Random random;

    public EvolvableString(String source) {
        super(source);
        sb = new StringBuilder();
        random = new Random();
    }
    
    @Override
    public double fitness(String source, String target) {
        double fitval = 0;
        for (var i = 0; i < source.length(); i++) {
            fitval += Math.abs(target.charAt(i) - source.charAt(i));
        }

        return fitval;
    }

    @Override
    public String mutate(String source) {
        var m = source;
        var i = random.nextInt(m.length());
        var flip = random.nextBoolean() ? 1 : -1;
        var mutation = m.charAt(i) + flip;

        if (mutation > 126) {
            mutation = 32;
        } else if (mutation < 32){
            mutation = 126;
        }

        return sb.delete(0, sb.length())
                .append(m.substring(0, i))
                .append(Character.toChars(mutation))
                .append(m.substring(i + 1, m.length()))
                .toString();
    }
}
