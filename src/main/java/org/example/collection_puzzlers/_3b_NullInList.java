package org.example.collection_puzzlers;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class _3b_NullInList {
    public static void main(String[] args) {
        String[] ints = {"a", "b", "c", null};

        /*
        Cannot add new items to this list returned by Arrays.asList()
        Note: Arrays.asList(arrayReference) will not create a new list. It creates a view of the array.
        Note: You cannot remove any element from the returned list.
        No structural change is allowed.
         */
        List<String> strings = Arrays.asList(ints);
        strings.removeIf(Objects::isNull);  //Exception in thread "main" java.lang.UnsupportedOperationException: remove
        System.out.print(strings.size());  // 4

    }
}
