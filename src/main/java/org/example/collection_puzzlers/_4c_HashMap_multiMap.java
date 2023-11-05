package org.example.collection_puzzlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Creating multi-map in java. One key has multiple values in the form of a list.
// Use computeIfAbsent() method to create such a multi-map.
public class _4c_HashMap_multiMap {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(-1, 0, 1);

        Map<Integer, List<Integer>> map = new HashMap<>();
        numbers.forEach(
                number -> map.computeIfAbsent(number, initialCapacity -> new ArrayList<Integer>(initialCapacity)).add(number)
        );  //Exception in thread "main" java.lang.IllegalArgumentException: Illegal Capacity: -1


//                numbers.forEach(
//                number -> map.putIfAbsent(number, new ArrayList<>()).add(number)
//        );
        System.out.print(map.get(0));
    }
}
