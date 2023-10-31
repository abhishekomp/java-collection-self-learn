package org.example.collection_puzzlers;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class _8_CollectionsEquality {
    public static void main(String[] args) {
        List<Integer> ints = List.of(1, 2, 3);

        List<Integer> a = Arrays.asList(1, 2, 3);
        Collection<Integer> c = Collections.unmodifiableCollection(ints);
        List<Integer> l = Collections.unmodifiableList(ints);

        System.out.print(a.equals(c) + " ");    //false
        System.out.print(a.equals(l) + " ");    //true
        System.out.print(c.equals(l));          //false

    }
}
