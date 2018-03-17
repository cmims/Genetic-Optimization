package com.slethron.evolution;

import java.util.Random;

public class EvolvableString extends Evolvable<String> {
    StringBuffer sb;
    Random random;

    public EvolvableString(String source) {
        super(source);
        sb = new StringBuffer();
        random = new Random();
    }

    @Override
    Double fitness(String source, String target) {
        double fitval = 0;
        for (int i = 0; i < source.length(); i++) {
            fitval += Math.abs(target.charAt(i) - source.charAt(i));
        }

        return fitval;
    }

    @Override
    String mutate(String source) {
        String m = source;
        int i = random.nextInt(m.length());
        int flip = random.nextBoolean() ? 1 : -1;
        int mutation = m.charAt(i) + flip;

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

    public static void main(String[] args) {
        String source = "!dlroW ,olleH";
        String target = "Hello, World!";

        EvolvableString se = new EvolvableString(source);
        se.solve(target);
    }
}
