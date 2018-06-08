package com.slethron.geneticoptimization.domain;

import java.util.Random;

public class KnapsackItem {
    private int itemId;
    private int value;
    private int weight;
    
    public KnapsackItem(int weight, int value) {
        itemId = new Random().nextInt();
        this.weight = weight;
        this.value = value;
    }
    
    private int getItemId() {
        return itemId;
    }
    
    public int getValue() {
        return value;
    }
    
    public int getWeight() {
        return weight;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnapsackItem)) return false;
        
        var item = (KnapsackItem) o;
        
        if (getItemId() != item.getItemId()) return false;
        if (getWeight() != item.getWeight()) return false;
        return getValue() == item.getValue();
    }
    
    @Override
    public int hashCode() {
        return itemId;
    }
    
    @Override
    public String toString() {
        return "(id=" + itemId + ", w=" + weight + ", v=" + value + ")";
    }
}