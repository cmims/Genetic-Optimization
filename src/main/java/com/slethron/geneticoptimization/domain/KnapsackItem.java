package com.slethron.geneticoptimization.domain;

import java.util.Objects;
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
        
        return getItemId() == item.getItemId();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(itemId, value, weight);
    }
    
    @Override
    public String toString() {
        return "(id=" + itemId + ", w=" + weight + ", v=" + value + ")";
    }
}