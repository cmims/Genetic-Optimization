package com.slethron.evolution;

import java.math.BigInteger;

public abstract class Evolvable<T> {
    private StringBuffer sb;
    private T source;

    private Evolvable() {
        sb = new StringBuffer();
    }

    Evolvable(T source) {
        this();
        setSource(source);
    }

    public void evolve(T target) {
        verify(source, target);
        Double fitval = fitness(source, target);
        BigInteger i = BigInteger.ZERO;
        while (true) {
            i = i.add(BigInteger.ONE);
            T m = mutate(source);
            Double fitval_m = fitness(m, target);
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

    public void setSource(T source) {
        this.source = source;
    }

    public T getSource() {
        return source;
    }

    abstract void verify(T source, T target) throws IllegalArgumentException;

    abstract Double fitness(T source, T target);

    abstract T mutate(T source);
}
