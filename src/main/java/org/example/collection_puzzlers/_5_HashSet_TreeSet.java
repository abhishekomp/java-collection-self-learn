package org.example.collection_puzzlers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.String.CASE_INSENSITIVE_ORDER;

public class _5_HashSet_TreeSet {
    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>(List.of("a", "b", "c"));
        Set<String> treeSet = new TreeSet<>(CASE_INSENSITIVE_ORDER);

        treeSet.addAll(List.of("A", "B", "C"));

        System.out.print(treeSet.equals(hashSet) + " ");
        System.out.print(hashSet.equals(treeSet));
    }
}
