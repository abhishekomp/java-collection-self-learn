package org.example.iterable.custom_IntCollection;

import java.util.Iterator;

public class IntegerCollectionRunner {
    public static void main(String[] args) {
        IntegerCollection intCollection = new IntegerCollection();

        for (Integer element : intCollection) {
            System.out.println("Element: " + element);
        }

        // Using the iterator directly
        Iterator<Integer> iterator = intCollection.iterator();
        while (iterator.hasNext()) {
            System.out.println("Iterator Element: " + iterator.next());
        }
    }
}
