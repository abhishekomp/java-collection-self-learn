package org.example.collection_puzzlers;

import java.util.HashMap;
import java.util.Map;

public class _4b_HashMap {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(4, null);

        System.out.print(map.getOrDefault(4, "four") + " ");

        map.putIfAbsent(4, "four");
        System.out.print(map.get(4));
    }
}
