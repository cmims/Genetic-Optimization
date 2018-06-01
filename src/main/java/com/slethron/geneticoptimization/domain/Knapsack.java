package com.slethron.geneticoptimization.domain;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    private int maxWeight;
    private List<KnapsackItem> items;
    
    public Knapsack(Knapsack knapsack) {
        maxWeight = knapsack.maxWeight;
        items = knapsack.items;
    }
    
    public Knapsack(int maxWeight) {
        this.maxWeight = maxWeight;
        this.items = new ArrayList<>();
    }
    
    public int getTotalWeight() {
        var totalWeight = 0;
        for (var item : items) {
            totalWeight += item.getWeight();
        }
        
        return totalWeight;
    }
    
    public int getTotalValue() {
        var totalValue = 0;
        for (var item : items) {
            totalValue += item.getValue();
        }
        
        return totalValue;
    }
    
    public List<KnapsackItem> getItems() {
        return items;
    }
    
    public int getMaxWeight() {
        return maxWeight;
    }
    
    public boolean put(KnapsackItem item) {
        if (canFitItem(item)) {
            items.add(item);
            return true;
        }
        
        return false;
    }
    
    public void remove(KnapsackItem item) {
        items.remove(item);
    }
    
    private boolean canFitItem(KnapsackItem item) {
        return getTotalWeight() + item.getWeight() <= maxWeight;
    }
    
    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append("Knapsack: {maxWeight=")
                .append(maxWeight)
                .append(", ");
        for (var i = 0; i < items.size(); i++) {
            builder.append(items.get(i).toString());
            if (i != items.size() - 1) {
                builder.append(", ");
            }
            
        }
        builder.append("}");
        
        return builder.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Knapsack)) return false;
        
        var knapsack = (Knapsack) o;
        
        for (var item : this.getItems()) {
            if (!knapsack.getItems().contains(item)) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public int hashCode() {
        int result = maxWeight;
        result = 31 * result + (getItems() != null ? getItems().hashCode() : 0);
        return result;
    }
}
