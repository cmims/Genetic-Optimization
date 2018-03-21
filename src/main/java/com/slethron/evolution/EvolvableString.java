package com.slethron.evolution;

import java.util.Random;

public class EvolvableString extends Evolvable<String> {
    private static final String LENGTH_N0T_EQUAL_ERR = "Source and target strings must have equal lengths";
    private static final String LENGTH_OF_ZERO_ERR = "Source and target strings must have length > 0";
    private static final String INVALID_CHAR_ERR = "Characters must be in UTF-16 range 32 <= x <= 126;";

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

    @Override
    void verify(String source, String target) throws IllegalArgumentException {
        if (source.length() != target.length()) {
            throw new IllegalArgumentException(LENGTH_N0T_EQUAL_ERR);
        } else if (source.length() == 0 || target.length() == 0) {
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
