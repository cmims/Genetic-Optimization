package com.slethron.evolution;

import java.math.BigInteger;
import java.util.Random;

public class StringEvolver {
    StringBuffer sb;
    Random random;

    public StringEvolver() {
        random = new Random();
        sb = new StringBuffer();
    }

    public void solve(String _source, String _target) throws IllegalArgumentException {
        String source = _source;
        String target = _target;
        if (source.length() != target.length() || source.length() == 0 || target.length() == 0) {
            throw new IllegalArgumentException();
        }

        double fitval = fitness(source, target);

        BigInteger i = BigInteger.ZERO;
        while (true) {
            i = i.add(BigInteger.ONE);
            String m = mutate(source);
            double fitval_m = fitness(m, target);

            boolean accepted = false;
            if (fitval_m < fitval) {
                fitval = fitval_m;
                source = m;
                accepted = true;
            }

            sb.delete(0, sb.length())
                    .append("index ")
                    .append(i)
                    .append(" : fitval_m ")
                    .append(fitval_m)
                    .append(" : m ")
                    .append(m)
                    .append(accepted ? " : accepted" : " : rejected");

            System.out.println(sb.toString());

            if (fitval == 0) {
                break;
            }
        }
    }

    private double fitness(String source, String target) {
        double fitval = 0;
        for (int i = 0; i < source.length(); i++) {
            fitval += Math.abs(target.charAt(i) - source.charAt(i));
        }

        return fitval;
    }

    private String mutate(String source) {
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

        StringEvolver se = new StringEvolver();
        se.solve(source, target);
    }
}
