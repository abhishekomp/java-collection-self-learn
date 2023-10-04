package org.example.iterable;

import java.util.*;

public class IterableExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        // Difference between Iterable and Iterator is that an Iterable is a source of Iterator
        // From an Iterable, it is possible to obtain an Iterator
        Collection<String> collection = list;
        Iterable<String> iterable = collection;

        // Ways to iterate over an iterable
        // Method 1
        for(String element : collection) {
            System.out.println(element);
        }

        // Method 2
        Iterator<String> iterator = iterable.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Method 3
        iterable.forEach(item -> System.out.println(item));

        Spliterator<String> spliterator = iterable.spliterator();
    }
}
