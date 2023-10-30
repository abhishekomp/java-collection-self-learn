package org.example.collection_puzzlers;

import java.util.HashMap;
import java.util.Map;

/*
putIfAbsent()
Returns: The previous value (value associated with the key before this function completes execution) associated with the key.
 */
public class _4b_HashMap_putIfAbsent {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, null);
        System.out.println(map.putIfAbsent(2, "twoAgain"));
        System.out.println(map.putIfAbsent(3, "three"));
        System.out.println(map.putIfAbsent(4, "four"));
        System.out.println(map.putIfAbsent(5, null));
        map.forEach((key, value) -> System.out.println(key + "->" + value));
        //map.entrySet().forEach(entry -> System.out.println(entry.getKey() + "->" + entry.getValue()));
    }
}
