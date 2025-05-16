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
        // An Iterable is a source of elements, while an Iterator is a cursor that allows you to traverse the elements.
        Collection<String> collection = list;
        Iterable<String> iterable = list;

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

        // Method 4
        // Spliterator is a special type of iterator that can be used to traverse elements in parallel
        // It is used in the Java Stream API to split a collection into smaller parts for parallel processing
        // Spliterator<String> spliterator = iterable.spliterator();
        // spliterator.forEachRemaining(System.out::println);
        // Method 5
        // Stream<String> stream = iterable.stream();
        // stream.forEach(System.out::println);
        // Method 6
        // Stream<String> stream = iterable.parallelStream();
        // stream.forEach(System.out::println);

    }
}
