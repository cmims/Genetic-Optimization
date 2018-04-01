package com.slethron.evolution.interfaces;

import java.math.BigInteger;

public abstract class TargetedEvolvable<E> implements Evolvable {
    private final StringBuilder sb = new StringBuilder();
    private E source;

    public TargetedEvolvable(E source) {
        this.source = source;
    }

    public void evolve(E target) {
        var fitval = fitness(source, target);
        var i = BigInteger.ZERO;
        while (fitval != 0) {
            i = i.add(BigInteger.ONE);
            var m = mutate(source);
            var fitvalM = fitness(m, target);
            var accepted = false;
            if (fitvalM < fitval) {
                fitval = fitvalM;
                source = m;
                accepted = true;
            }
            
            /*
            Move Following code to another class
             */
            sb.delete(0, sb.length())
                    .append("index ")
                    .append(i)
                    .append(" : fitvalM ")
                    .append(fitvalM)
                    .append(" : m ")
                    .append(m)
                    .append(accepted ? " : accepted" : " : rejected");
            System.out.println(sb.toString());
        }
    }

    public E getSource() {
        return source;
    }

    public abstract double fitness(E source, E target);

    public abstract E mutate(E source);
}
