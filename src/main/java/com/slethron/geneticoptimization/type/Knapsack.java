package com.slethron.geneticoptimization.type;

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
        items.add(item);
        var totalWeight = getTotalWeight();
        items.remove(item);
        
        return totalWeight <= maxWeight;
    }
    
    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append("Knapsack: {\n\tmaxWeight = ")
                .append(maxWeight);
        for (var item : items) {
            builder.append(",\n\t")
                    .append(item.toString());
        }
        builder.append("\n}");
        
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
}
