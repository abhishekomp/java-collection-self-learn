package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private long inventoryId;
    private Location location;
    private Map<String, Integer> items;

    public Inventory(Long inventoryId, Location location, Map<String, Integer> items) {
        this.inventoryId = inventoryId;
        this.location = location;
        this.items = items;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public Location getLocation() {
        return location;
    }

    public Map<String, Integer> getItems() {
        return new HashMap<>(items);
    }

    @Override
    public String toString() {
        return "InventoryId: " + inventoryId + " " +
                "Location: " + location + " " +
                "Items: " + items;
    }

    public static class Location {
        private String state;
        public Location(String state) {
            this.state = state;
        }
        public String getState() {
            return state;
        }
        @Override
        public String toString() {
            return state;
        }
    }

    public static List<Inventory> getInventories(){
        List<Inventory> inventories = new ArrayList<>();

        Map<String, Integer> items = new HashMap<>();
        items.put("Coke", 20);
        items.put("Toothpaste", 10);
        inventories.add(new Inventory(20L, new Location("Oregon"), items));

        items = new HashMap<>();
        items.put("Mattress", 125);
        inventories.add(new Inventory(10L, new Location("Ohio"), items));

        items = new HashMap<>();
        items.put("Cheese", 24);
        items.put("Milk", 21);
        inventories.add(new Inventory(15L, new Location("Alaska"), items));

        return inventories;
    }
}
