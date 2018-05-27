package com.slethron.geneticoptimization.type;

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
    
    int getValue() {
        return value;
    }
    
    int getWeight() {
        return weight;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnapsackItem)) return false;
        
        KnapsackItem that = (KnapsackItem) o;
        
        return getItemId() == that.getItemId();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(itemId, value, weight);
    }
    
    @Override
    public String toString() {
        return "Item: {\nItemId = " + itemId
                + ",\n weight = " + weight
                + ",\n value = " + value
                + "\n}";
    }
}