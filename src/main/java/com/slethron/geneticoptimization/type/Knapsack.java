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
    
    public int getTotalValue() {
        var totalValue = 0;
        for (KnapsackItem item : items) {
            totalValue += item.getValue();
        }
        
        return totalValue;
    }
    
    public List<KnapsackItem> getItems() {
        return items;
    }
    
    public boolean canFitItem(KnapsackItem item) {
        items.add(item);
        var totalWeight = getTotalWeight();
        items.remove(item);
        
        return totalWeight <= maxWeight;
    }
    
    public void replaceItem(KnapsackItem item, KnapsackItem replacementItem) {
        items.remove(item);
        items.add(replacementItem);
    }
    
    public boolean put(KnapsackItem item) {
        if (canFitItem(item)) {
            items.add(item);
            return true;
        }
        
        return false;
    }
    
    private int getTotalWeight() {
        var totalWeight = 0;
        for (KnapsackItem item : items) {
            totalWeight += item.getWeight();
        }
        
        return totalWeight;
    }
    
    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append("Knapsack: {\n\tmaxWeight = ")
                .append(maxWeight);
        for (KnapsackItem item : items) {
            builder.append(",\n\t")
                    .append(item.toString());
        }
        builder.append("\n}");
        
        return builder.toString();
    }
}
