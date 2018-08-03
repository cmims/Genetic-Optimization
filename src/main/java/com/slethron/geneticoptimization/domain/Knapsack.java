package com.slethron.geneticoptimization.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Knapsack {
    public static class KnapsackItem {
        private int itemId;
        private int value;
        private int weight;
    
        public KnapsackItem(int itemId, int weight, int value) {
            this.itemId = itemId;
            this.weight = weight;
            this.value = value;
        }
    
        public int getItemId() {
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
            return "id=" + itemId + ", w=" + weight + ", v=" + value;
        }
    }
    
    private int maxWeight;
    private Set<KnapsackItem> items;

    public Knapsack(Knapsack knapsack) {
        maxWeight = knapsack.maxWeight;
        items = knapsack.items;
    }

    public Knapsack(int maxWeight) {
        this.maxWeight = maxWeight;
        this.items = new HashSet<>();
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
        return new ArrayList<>(items);
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

    private boolean canFitItem(KnapsackItem item) {
        return getTotalWeight() + item.getWeight() <= maxWeight;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder.append("Knapsack: {maxWeight=")
                .append(maxWeight)
                .append(", ");
        var knapsackItems = getItems();
        for (var i = 0; i < knapsackItems.size(); i++) {
            builder.append("item")
                    .append(i)
                    .append("=(")
                    .append(knapsackItems.get(i).toString())
                    .append(")");
            if (i != knapsackItems.size() - 1) {
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
        return maxWeight + items.hashCode();
    }
}
