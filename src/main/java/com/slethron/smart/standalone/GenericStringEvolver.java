package com.slethron.smart.standalone;

import java.math.BigInteger;
import java.util.Random;

public class GenericStringEvolver {
    private static final String LENGTH_N0T_EQUAL_ERR = "Source and target strings must have equal lengths";
    private static final String LENGTH_OF_ZERO_ERR = "Source and target strings must have length > 0";
    private static final String INVALID_CHAR_ERR = "Characters must be in UTF-16 range 32 <= x <= 126;";
    
    private StringBuilder sb;
    private Random random;
    
    private int iLast;
    private int opLast;
    private boolean accepted;
    
    private String source;
    
    public GenericStringEvolver(String source) {
        sb = new StringBuilder();
        random = new Random();
    
        iLast = 0;
        opLast = 0;
        accepted = false;
        
        this.source = source;
    }
    
    public void evolve(String target) {
        verify(source, target);
        Double fitval = fitness(source, target);
        BigInteger i = BigInteger.ZERO;
        while (fitval != 0) {
            i = i.add(BigInteger.ONE);
            String m = mutate(source);
            Double fitval_m = fitness(m, target);
            
            if (fitval_m < fitval) {
                fitval = fitval_m;
                source = m;
                accepted = true;
            } else {
                accepted = false;
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
        }
    }
    
    public String getSource() {
        return source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    private static Double fitness(String source, String target) {
        Double fitval = 0D;
        for (int i = 0; i < source.length(); i++) {
            fitval += Math.abs(target.charAt(i) - source.charAt(i));
        }
        
        return fitval;
    }
    
    private String mutate(String source) {
        String m = source;
        int i;
        int op;
        if (accepted) {
            i = iLast;
            op = opLast;
            
            
        } else {
            i = random.nextInt(m.length());
            op = random.nextBoolean() ? 1 : -1;
            
            iLast = i;
            opLast = op;
        }
        
        int mutation = m.charAt(i) + op;
        
        if (mutation > 126) {
            mutation = 32;
        } else if (mutation < 32) {
            mutation = 126;
        }
        
        return sb.delete(0, sb.length())
                .append(m.substring(0, i))
                .append(Character.toChars(mutation))
                .append(m.substring(i + 1, m.length()))
                .toString();
    }
    
    private void verify(String source, String target) {
        if (source.length() != target.length()) {
            throw new IllegalArgumentException(LENGTH_N0T_EQUAL_ERR);
        } else if (source.length() == 0) {
            throw new IllegalArgumentException(LENGTH_OF_ZERO_ERR);
        } else {
            for (char c : source.toCharArray()) {
                if (c < 32 || c > 126) {
                    throw new IllegalArgumentException(INVALID_CHAR_ERR);
                }
            }
            for (char c : target.toCharArray()) {
                if (c < 32 || c > 126) {
                    throw new IllegalArgumentException(INVALID_CHAR_ERR);
                }
            }
        }
    }
}
