package com.slethron.geneticoptimization.type;

import java.util.Objects;

public class BitString {
    private boolean[] bits;
    
    public BitString(boolean[] bits) {
        this.bits = bits;
    }
    
    public BitString(BitString source) {
        this.bits = source.bits.clone();
    }
    
    public boolean get(int column) {
        return bits[column];
    }
    
    public void set(int column, boolean row) {
        bits[column] = row;
    }
    
    public int length() {
        return bits.length;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        
        if (!(obj instanceof BitString)) {
            return false;
        }
        
        var e = (BitString) obj;
        
        if (e.length() != length()) {
            return false;
        }
        
        for (var i = 0; i < length(); i++) {
            if (e.get(i) != get(i)) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public String toString() {
        var builder = new StringBuilder();
        for (var column = 0; column < bits.length; column++) {
            builder.append(bits[column]);
            if (column != bits.length - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        
        return builder.toString();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(bits, bits.length);
    }
}
