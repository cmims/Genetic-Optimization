package com.slethron.evolution.interfaces;

import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.*;

public abstract class NaturalEvolvable<E> implements Evolvable {
    private Logger logger;
    private FileHandler fileHandler;
    private StringBuilder sb;
    private E source;
    
    public NaturalEvolvable(E source) {
        this.source = source;
        logger = Logger.getLogger("gen.log");
        
        try {
            fileHandler = new FileHandler("log/gen.log");
            logger.addHandler(fileHandler);
            logger.info(
            );
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public void evolve() {
        var sourceFitVal = fitness(source);
        var i = BigInteger.ZERO;
        while (sourceFitVal != 0) {
            i = i.add(BigInteger.ONE);
            var mutated = mutate(source);
            var mutatedFitVal = fitness(mutated);
            if (mutatedFitVal < sourceFitVal) {
                sourceFitVal = mutatedFitVal;
                source = mutated;
            }
    
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
    
    public abstract double fitness(E source);
    
    public abstract E mutate(E source);
}
