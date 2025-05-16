package org.example.contains_method;

import java.util.ArrayList;
import java.util.List;

public class Main {
    // Invoking contains method on the Collection of Products
    public static void main(String[] args) {
        Product door = new Product("Wooden Door", 35);
        Product floorPanel = new Product("Floor Panel", 25);
        Product window = new Product("Glass Window", 10);

        Product duplicateWindow = new Product("Glass Window", 10);

        // Create a collection of products
        List<Product> products = new ArrayList<>();
        products.add(door);
        products.add(floorPanel);
        products.add(window);

        // Check if the collection contains a specific product
        boolean containsProduct = products.contains(window);    // This will return true because the equals method is not overridden in the Product class but the reference is the same
        System.out.println("Contains window: " + containsProduct);

        // Check if the collection contains a duplicate product
        boolean containsDuplicate = products.contains(duplicateWindow);
        System.out.println("Contains duplicate window: " + containsDuplicate);  // This will return false because the equals method is not overridden in the Product class
    }
}
